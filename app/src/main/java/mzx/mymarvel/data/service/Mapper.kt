package mzx.mymarvel.data.service

import mzx.mymarvel.data.entity.*
import mzx.mymarvel.data.net.model.*
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun map(result: Result): CharacterEntity = CharacterEntity(
        comics = map(result.comics),
        description = result.description,
        events = map(result.events),
        id = result.id,
        modified = result.modified,
        name = result.name,
        resourceURI = result.resourceURI,
        series = map(result.series),
        stories = map(result.stories),
        thumbnail = map(result.thumbnail),
        urls = result.urls.map(::map)
    )

    private fun map(url: Url): UrlEntity = UrlEntity(url = url.url, type = url.type)

    private fun map(thumbnail: Thumbnail): ThumbnailEntity =
        ThumbnailEntity(extension = thumbnail.extension, path = thumbnail.path)

    private fun map(stories: Stories): StoriesEntity = StoriesEntity(
        available = stories.available,
        collectionURI = stories.collectionURI,
        items = stories.items.map(::map),
        returned = stories.returned
    )

    private fun map(series: Series): SeriesEntity = SeriesEntity(
        available = series.available,
        collectionURI = series.collectionURI,
        items = series.items.map(::map),
        returned = series.returned
    )

    private fun map(events: Events): EventsEntity = EventsEntity(
        available = events.available,
        collectionURI = events.collectionURI,
        items = events.items.map(::map),
        returned = events.returned
    )

    private fun map(comics: Comics): ComicsEntity = ComicsEntity(
        available = comics.available,
        collectionURI = comics.collectionURI,
        items = comics.items.map(::map),
        returned = comics.returned
    )

    fun map(errorCode: Int): DataError = DataError.Server(errorCode)

    private fun map(item: Item): ItemEntity =
        ItemEntity(name = item.name, resourceURI = item.resourceURI, type = item.type)
}