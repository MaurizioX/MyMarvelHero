package mzx.mymarvel.domain.usecase

import arrow.core.Either
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.element.DomainError

interface GetCharacterListUseCase {
    suspend fun action(): Either<DomainError, List<CharacterElement>>

}
