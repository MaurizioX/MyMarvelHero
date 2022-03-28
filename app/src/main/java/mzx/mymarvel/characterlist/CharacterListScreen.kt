package mzx.mymarvel.characterlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import mzx.mymarvel.ui.model.State

@Composable
fun CharacterListScreen(
    characterListState: CharacterListViewModel.CharacterListState,
    eventHandler: (CharacterListViewModel.CharacterListEvent) -> Unit
) {
    LaunchedEffect(key1 = characterListState) {
        if (characterListState.isLoading == State.INIT) {
            eventHandler(CharacterListViewModel.CharacterListEvent.Init)
        }
    }
}