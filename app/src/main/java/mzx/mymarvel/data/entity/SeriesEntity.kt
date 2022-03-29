package mzx.mymarvel.data.entity

data class SeriesEntity(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemEntity>,
    val returned: Int
)
