package mzx.mymarvel.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavigationDirections {

    val Default = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""

    }

    val marvelList = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "marvelList"

    }

}

object CharacterDetailNavigation {

    const val KEY_CHARACTER_ID = "characterId"
    val route = "character/character?$KEY_CHARACTER_ID={$KEY_CHARACTER_ID}"
    val currentArguments = listOf(
        navArgument(KEY_CHARACTER_ID) { type = NavType.IntType }
    )

    fun characterDetail(
        characterId: Int,
    ) = object : NavigationCommand {

        override val arguments = currentArguments

        override val destination = "character/character?$KEY_CHARACTER_ID=$characterId"
    }
}