package mzx.mymarvel.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import mzx.mymarvel.navigation.NavigationDirections.Default


class NavigationManager {
    val commands = MutableStateFlow(Default)

    var navController: NavHostController? = null

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
//        navController.navigate(directions = directions.destination)
    }
}