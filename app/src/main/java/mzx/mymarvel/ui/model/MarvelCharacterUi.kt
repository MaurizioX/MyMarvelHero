package mzx.mymarvel.ui.model

data class MarvelCharacterUi(
    val name: String,
    val url: String,
    val modifiedDate: String,
    val description: String,
    val characterId: Int,
    val comics: List<MarvelInfoUi>,
    val series: List<MarvelInfoUi>,
    val stories: List<MarvelInfoUi>
) {
    val hasComics: Boolean = comics.isNotEmpty()
    val hasSeries: Boolean = series.isNotEmpty()
    val hasStories: Boolean = stories.isNotEmpty()
}

data class MarvelInfoUi(val name: String)