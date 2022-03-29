package mzx.mymarvel.domain.usecase.impl

import arrow.core.Either
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.data.mapper.DomainMapper
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import javax.inject.Inject

class GetCharacterListUseCaseImpl @Inject constructor(
    private val characterService: CharacterService,
    private val mapper: DomainMapper
) :
    GetCharacterListUseCase {
    override suspend fun action(): Either<DataError, List<CharacterEntity>> =
        characterService.getAllCharacters()
}