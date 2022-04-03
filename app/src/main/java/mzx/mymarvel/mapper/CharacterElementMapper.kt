package mzx.mymarvel.mapper

import mzx.mymarvel.data.entity.CharacterEntity
import mzx.mymarvel.ui.model.MarvelCharacterUi
import javax.inject.Inject

class CharacterElementMapper @Inject constructor() {
    fun map(characterElement: CharacterEntity): MarvelCharacterUi =
        MarvelCharacterUi(name = characterElement.name, url = characterElement.resourceURI)
}
