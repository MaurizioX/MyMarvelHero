package mzx.mymarvel.domain.usecase

import arrow.core.Either
import mzx.mymarvel.data.entity.DataError

interface GetCharacterDetailUseCase {
    suspend fun action(characterID: String): Either<DataError, String>
}