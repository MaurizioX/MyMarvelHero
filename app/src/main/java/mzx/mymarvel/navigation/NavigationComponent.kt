package mzx.mymarvel.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mzx.mymarvel.characterlist.CharacterListScreen
import mzx.mymarvel.characterlist.CharacterListViewModel

@Composable
fun NavigationComponent(navController: NavHostController, activity: Activity?) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.marvelList.destination
    ) {

        composable(NavigationDirections.marvelList.destination) {
            val viewModel: CharacterListViewModel = hiltViewModel<CharacterListViewModel>(it)
            CharacterListScreen(
                viewModel.state,
                viewModel::onEvent
            )
        }
    }
}