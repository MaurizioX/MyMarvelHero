package mzx.mymarvel.domain.element

import java.util.*

data class CharacterElement(
    val description: String,
    val events: CharacterInfoElement,
    val id: Int,
    val modified: Date,
    val name: String,
    val resourceURI: String,
    val comics: CharacterInfoElement,
    val series: CharacterInfoElement,
    val stories: CharacterInfoElement,
    val thumbnail: ThumbnailElement,
    val urls: List<UrlElement>
)
