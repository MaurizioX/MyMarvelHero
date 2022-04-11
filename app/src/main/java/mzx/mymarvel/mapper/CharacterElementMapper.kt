package mzx.mymarvel.mapper

import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.data.entity.ItemEntity
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.MarvelInfoUi
import java.text.DateFormat
import javax.inject.Inject

class CharacterElementMapper @Inject constructor() {
    fun map(characterElement: CharacterEntity): MarvelCharacterUi =
        MarvelCharacterUi(
            name = characterElement.name,
            url = "${characterElement.thumbnail.path}.${characterElement.thumbnail.extension}",
            modifiedDate = df.format(characterElement.modified),
            description = characterElement.description,
            characterId = characterElement.id,
            comics = characterElement.comics.items.map(::map),
            series = characterElement.series.items.map(::map),
            stories = characterElement.stories.items.map(::map)
        )

    private fun map(itemEntity: ItemEntity): MarvelInfoUi = MarvelInfoUi(itemEntity.name)
}

private val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)