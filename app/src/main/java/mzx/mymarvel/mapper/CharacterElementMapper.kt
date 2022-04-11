package mzx.mymarvel.mapper

import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.ItemEntity
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.MarvelInfoUi
import javax.inject.Inject

class CharacterElementMapper @Inject constructor(private val dateMapper: DateMapper) {
    fun map(characterElement: CharacterEntity): MarvelCharacterUi =
        MarvelCharacterUi(
            name = characterElement.name,
            url = "${characterElement.thumbnail.path}.${characterElement.thumbnail.extension}",
            modifiedDate = dateMapper.mediumFormat(characterElement.modified),
            description = characterElement.description,
            characterId = characterElement.id,
            comics = characterElement.comics.items.map(::map),
            series = characterElement.series.items.map(::map),
            stories = characterElement.stories.items.map(::map)
        )

    private fun map(itemEntity: ItemEntity): MarvelInfoUi = MarvelInfoUi(itemEntity.name)
}