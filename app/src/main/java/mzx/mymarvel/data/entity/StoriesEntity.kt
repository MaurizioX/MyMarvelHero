package mzx.mymarvel.data.entity

data class StoriesEntity(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemEntity>,
    val returned: Int
)
