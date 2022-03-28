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