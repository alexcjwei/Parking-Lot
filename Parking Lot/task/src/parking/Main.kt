package parking

fun printPark(parkingLot: ParkingLot, registration: String, color: String) {
    try {
        val spot = parkingLot.park(registration, color)
        println("$color car parked in spot ${spot.num}.")
    } catch (err: ParkingException.ParkingLotFullException) {
        println(err.message)
    }
}

fun printLeave(parkingLot: ParkingLot, spotNum: Int) {
    try {
        val spot = parkingLot.leave(spotNum)
        println("Spot ${spot.num} is free.")
    } catch (err: ParkingException.ParkingSpotEmptyException) {
        println(err.message)
    }
}

fun printRegistrationsByColor(parkingLot: ParkingLot, color: String) {
    val spots = parkingLot.getFilledSpots()
    val regs = spots
        .filter { it.car!!.color.lowercase() == color.lowercase() }
        .map { it.car!!.registration }
    if (regs.isNotEmpty()) println(regs.joinToString()) else println("No cars with color $color were found.")
}

fun printSpotsByColor(parkingLot: ParkingLot, color: String) {
    val spots = parkingLot.getFilledSpots()
    val spotNums = spots
        .filter { it.car!!.color.lowercase() == color.lowercase() }
        .map { it.num }
    if (spotNums.isNotEmpty()) println(spotNums.joinToString()) else println("No cars with color $color were found.")
}

fun printSpotByRegistration(parkingLot: ParkingLot, registration: String) {
    val spot = parkingLot
        .getFilledSpots()
        .find { it.car!!.registration.lowercase() == registration.lowercase() }
    if (spot != null) println(spot.num) else println("No cars with registration number $registration were found.")
}

fun printStatus(parkingLot: ParkingLot) {
    val spots = parkingLot.getFilledSpots()
    if (spots.isEmpty()) {
        println("Parking lot is empty.")
    } else {
        spots.forEach { spot ->
            println("${spot.num} ${spot.car?.registration} ${spot.car?.color}")
        }
    }
}

fun createParkingLot(numSpots: Int): ParkingLot {
    val lot = ParkingLot(numSpots)
    println("Created a parking lot with $numSpots spots.")
    return lot
}

fun main() {
    var parkingLot: ParkingLot? = null
    while (true) {
        val input = readln().splitToSequence(' ').toList()
        when {
            input[0] == "exit" -> return
            input[0] == "create" -> parkingLot = createParkingLot(input[1].toInt())
            parkingLot == null -> println("Sorry, a parking lot has not been created.")
            input[0] == "status" -> printStatus(parkingLot)
            input[0] == "park" -> printPark(parkingLot, input[1], input[2])
            input[0] == "leave" -> printLeave(parkingLot, input[1].toInt())
            input[0] == "reg_by_color" -> printRegistrationsByColor(parkingLot, input[1])
            input[0] == "spot_by_color" -> printSpotsByColor(parkingLot, input[1])
            input[0] == "spot_by_reg" -> printSpotByRegistration(parkingLot, input[1])
        }
    }
}
