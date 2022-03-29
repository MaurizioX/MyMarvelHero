package mzx.mymarvel.domain.usecase

import arrow.core.Either
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.element.CharacterElement

interface GetCharacterListUseCase {
    suspend fun action(): Either<DataError, List<CharacterEntity>>

}
