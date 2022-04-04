package mzx.mymarvel.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mzx.mymarvel.characterdetail.CharacterDetailScreen
import mzx.mymarvel.characterdetail.CharacterDetailViewModel
import mzx.mymarvel.characterlist.CharacterListScreen
import mzx.mymarvel.characterlist.CharacterListViewModel

@Composable
fun NavigationComponent(navController: NavHostController, activity: Activity?) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.marvelList.destination
    ) {

        composable(NavigationDirections.marvelList.destination) {
            val viewModel: CharacterListViewModel = hiltViewModel(it)
            CharacterListScreen(
                viewModel.state,
                viewModel::onEvent,
                navController
            )
        }
        composable(CharacterDetailNavigation.route) { backStackEntry ->
            val characterId =
                backStackEntry.arguments?.getString(CharacterDetailNavigation.KEY_CHARACTER_ID)
                    ?.toInt()
                    ?: throw IllegalStateException("Argument is required")
            val viewModel: CharacterDetailViewModel = hiltViewModel(backStackEntry)
            CharacterDetailScreen(
                characterId,
                viewModel.state,
                viewModel::onEvent
            )
        }
    }
}