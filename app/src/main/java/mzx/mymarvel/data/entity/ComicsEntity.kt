package mzx.mymarvel.data.entity

data class ComicsEntity(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemEntity>,
    val returned: Int
)
