package mzx.mymarvel.mapper

import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.ui.model.MarvelCharacterUi
import java.text.DateFormat
import javax.inject.Inject

class CharacterElementMapper @Inject constructor() {
    fun map(characterElement: CharacterEntity): MarvelCharacterUi =
        MarvelCharacterUi(
            name = characterElement.name,
            url = "${characterElement.thumbnail.path}.${characterElement.thumbnail.extension}",
            modifiedDate = df.format(characterElement.modified),
            description = characterElement.description,
            characterId = characterElement.id
        )
}

private val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)