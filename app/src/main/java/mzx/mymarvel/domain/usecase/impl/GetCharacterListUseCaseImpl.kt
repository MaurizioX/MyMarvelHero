package mzx.mymarvel.domain.usecase.impl

import arrow.core.Either
import mzx.mymarvel.data.mapper.DomainMapper
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.usecase.DomainError
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import javax.inject.Inject

class GetCharacterListUseCaseImpl @Inject constructor(
    private val characterService: CharacterService,
    private val mapper: DomainMapper
) :
    GetCharacterListUseCase {
    override suspend fun action(): Either<DomainError, List<CharacterElement>> =
        characterService.getAllCharacters().fold(mapper::map, mapper::map)
}