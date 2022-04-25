package mzx.mymarvel.data.mapper

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import mzx.mymarvel.data.entity.*
import mzx.mymarvel.domain.element.*
import javax.inject.Inject

class DomainMapper @Inject constructor() {
    fun map(dataError: DataError) = when (dataError) {
        is DataError.Server -> DomainError.Server(dataError.type)
        DataError.NoBodyResponse -> DomainError.NoBodyResponse
    }.left()

    fun map(characterEntities: List<CharacterEntity>): Either<DomainError, List<CharacterElement>> =
        characterEntities.map(::mapEntity).right()

    fun map(characterEntity: CharacterEntity): Either<DomainError, CharacterElement> =
        mapEntity(characterEntity).right()


    private fun mapEntity(characterEntity: CharacterEntity): CharacterElement = CharacterElement(
        description = characterEntity.description,
        comics = map(characterEntity.comics),
        id = characterEntity.id,
        modified = characterEntity.modified,
        name = characterEntity.name,
        resourceURI = characterEntity.resourceURI,
        series = map(characterEntity.series),
        stories = map(characterEntity.stories),
        thumbnail = map(characterEntity.thumbnail),
        urls = characterEntity.urls.map(::map),
        events = map(characterEntity.events)
    )

    private fun map(thumbnail: ThumbnailEntity): ThumbnailElement =
        ThumbnailElement(thumbnail.extension, thumbnail.path)

    fun map(characterInfoEntity: CharacterInfoEntity): CharacterInfoElement = CharacterInfoElement(
        characterInfoEntity.available,
        characterInfoEntity.collectionURI,
        characterInfoEntity.items.map(::map),
        characterInfoEntity.returned
    )

    fun map(urlEntity: UrlEntity): UrlElement = UrlElement(urlEntity.type, urlEntity.url)

    fun map(itemEntity: ItemEntity): ItemElement =
        ItemElement(itemEntity.name, itemEntity.resourceURI, itemEntity.type)
}