package mzx.mymarvel.characterlist.domain.usecase.impl

import arrow.core.Either
import mzx.mymarvel.characterlist.domain.element.CharacterElement
import mzx.mymarvel.characterlist.domain.usecase.DomainError
import mzx.mymarvel.characterlist.domain.usecase.GetCharacterListUseCase
import javax.inject.Inject

class GetCharacterListUseCaseImpl @Inject constructor() : GetCharacterListUseCase {
    override suspend fun action(): Either<DomainError, List<CharacterElement>> {
        TODO("Not yet implemented")
    }
}