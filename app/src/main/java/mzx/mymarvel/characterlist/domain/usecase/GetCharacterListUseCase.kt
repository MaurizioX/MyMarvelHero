package mzx.mymarvel.characterlist.domain.usecase

import arrow.core.Either
import mzx.mymarvel.characterlist.domain.element.CharacterElement

interface GetCharacterListUseCase {
    suspend fun action(): Either<DomainError, List<CharacterElement>>

}
