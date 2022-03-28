package mzx.mymarvel.domain.usecase

import arrow.core.Either
import mzx.mymarvel.domain.element.CharacterElement

interface GetCharacterListUseCase {
    suspend fun action(): Either<DomainError, List<CharacterElement>>

}
