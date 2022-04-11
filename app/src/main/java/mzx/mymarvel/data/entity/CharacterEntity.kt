package mzx.mymarvel.data.entity

import java.util.*

data class CharacterEntity(
    val description: String,
    val events: EventsEntity,
    val id: Int,
    val modified: Date,
    val name: String,
    val resourceURI: String,
    val comics: ComicsEntity,
    val series: SeriesEntity,
    val stories: StoriesEntity,
    val thumbnail: ThumbnailEntity,
    val urls: List<UrlEntity>
)
