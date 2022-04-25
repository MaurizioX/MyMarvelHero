package mzx.mymarvel.domain.usecase.impl

import arrow.core.Either
import mzx.mymarvel.data.mapper.DomainMapper
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.element.DomainError
import mzx.mymarvel.domain.usecase.GetCharacterDetailUseCase
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterService: CharacterService,
    private val mapper: DomainMapper
) :
    GetCharacterDetailUseCase {
    override suspend fun action(characterID: Int): Either<DomainError, CharacterElement> =
        characterService.fetchCharacterDetail(characterID).fold(mapper::map, mapper::map)
}