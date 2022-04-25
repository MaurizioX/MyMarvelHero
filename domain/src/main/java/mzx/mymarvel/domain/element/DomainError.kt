package mzx.mymarvel.domain.element

interface DomainError {
    data class Server(val type: Int) : DomainError
    object NoBodyResponse : DomainError

}
