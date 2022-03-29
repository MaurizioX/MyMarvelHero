package mzx.mymarvel.data.entity

interface DataError {
    data class Server(val type: Int) : DataError
    object NoBodyResponse : DataError
}
