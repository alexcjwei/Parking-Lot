package parking

sealed class QueryException(message: String) : Exception(message){
    class CarNotFoundException(message: String) : QueryException(message)
}