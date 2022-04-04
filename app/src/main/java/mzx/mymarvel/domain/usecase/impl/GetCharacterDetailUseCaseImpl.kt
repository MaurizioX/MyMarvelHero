package mzx.mymarvel.domain.usecase.impl

import arrow.core.Either
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.domain.usecase.GetCharacterDetailUseCase
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(private val characterService: CharacterService) :
    GetCharacterDetailUseCase {
    override suspend fun action(characterID: String): Either<DataError, String> = characterService.fetchCharacterDetail(characterID)
}