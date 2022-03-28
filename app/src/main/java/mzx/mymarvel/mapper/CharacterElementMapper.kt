package mzx.mymarvel.mapper

import mzx.mymarvel.domain.element.CharacterElement
import mzx.mymarvel.ui.model.MarvelCharacterUi
import javax.inject.Inject

class CharacterElementMapper @Inject constructor() {
    fun map(characterElement: CharacterElement): MarvelCharacterUi = MarvelCharacterUi()

}
