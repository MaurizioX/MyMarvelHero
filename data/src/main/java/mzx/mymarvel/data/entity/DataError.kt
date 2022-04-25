package mzx.mymarvel.data.entity

sealed interface DataError {
    data class Server(val type: Int) : DataError
    object NoBodyResponse : DataError
}
