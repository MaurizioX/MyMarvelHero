package mzx.mymarvel.data.service

import arrow.core.Either
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError

interface CharacterService {
    suspend fun getAllCharacters(): Either<DataError, List<CharacterEntity>>
}
