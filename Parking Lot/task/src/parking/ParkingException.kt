package parking

sealed class ParkingException(message: String) : Exception(message){
    class ParkingLotFullException : ParkingException("Sorry, the parking lot is full.")
    class ParkingSpotEmptyException(message: String) : ParkingException(message)
}