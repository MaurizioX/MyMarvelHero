package mzx.mymarvel.data.mapper

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.DataError
import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.domain.usecase.DomainError
import javax.inject.Inject

class DomainMapper @Inject constructor() {
    fun map(dataError: DataError): Either<DomainError, List<CharacterElement>> = DomainError.Unknown.left()

    fun map(characterEntities: List<CharacterEntity>):  Either<DomainError, List<CharacterElement>>
        = emptyList<CharacterElement>().right()

}