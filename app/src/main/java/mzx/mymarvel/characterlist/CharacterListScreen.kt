package mzx.mymarvel.characterlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import mzx.mymarvel.R
import mzx.mymarvel.ui.model.MarvelCharacterUi
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

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(characterListState.characters) { item ->
            CharacterItem(item)
        }
    }

}

@Composable
fun CharacterItem(marvelCharacterUi: MarvelCharacterUi) {
    Row {
        Card(border = BorderStroke(1.dp, Color.Black)) {
            Image(
                painter = rememberImagePainter(marvelCharacterUi.url),
                contentDescription = stringResource(
                    id = R.string.marvel_hero_img
                ),
                modifier = Modifier.size(40.dp)
            )
            Text(text = marvelCharacterUi.name, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
@Preview
fun CharacterItemPreview() {
    CharacterItem(marvelCharacterUi = MarvelCharacterUi("Hulk", "an url"))
}