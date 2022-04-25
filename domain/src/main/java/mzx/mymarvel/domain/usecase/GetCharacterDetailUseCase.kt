package mzx.mymarvel.domain.usecase

import arrow.core.Either
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.element.DomainError

interface GetCharacterDetailUseCase {
    suspend fun action(characterID: Int): Either<DomainError, CharacterElement>
}
