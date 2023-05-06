package parking

class ParkingLot(
    numSpots: Int
) {
    private val spots = List(numSpots) { ParkingSpot(it + 1, null) }
    private var numCars = 0


    fun leave(num: Int): ParkingSpot {
        val spot = spots[num - 1]
        when (spot.car) {
            null -> throw ParkingException.ParkingSpotEmptyException("There is no car in spot $num.")
            else -> {
                numCars--
                spot.car = null
                return spot
            }
        }
    }

    fun park(registrationNum: String, color: String): ParkingSpot {
        if (numCars == spots.size) {
            throw ParkingException.ParkingLotFullException()
        }
        for (spot in spots) {
            if (spot.car == null) {
                spot.car = Car(registrationNum, color)
                numCars++
                return spot
            }
        }
        throw ParkingException.ParkingLotFullException()
    }

    fun getFilledSpots() = spots.filter { spot -> spot.car != null }
}