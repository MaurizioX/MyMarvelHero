package mzx.mymarvel.data.entity

import java.util.*

data class CharacterEntity(
    val description: String,
    val events: CharacterInfoEntity,
    val id: Int,
    val modified: Date,
    val name: String,
    val resourceURI: String,
    val comics: CharacterInfoEntity,
    val series: CharacterInfoEntity,
    val stories: CharacterInfoEntity,
    val thumbnail: ThumbnailEntity,
    val urls: List<UrlEntity>
)
