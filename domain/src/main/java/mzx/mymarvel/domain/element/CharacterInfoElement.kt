package mzx.mymarvel.domain.element

data class CharacterInfoElement(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemElement>,
    val returned: Int
)
