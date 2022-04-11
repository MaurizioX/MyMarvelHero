package mzx.mymarvel.characterlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import mzx.mymarvel.R
import mzx.mymarvel.characterdetail.DisplayLoadingScreen
import mzx.mymarvel.navigation.CharacterDetailNavigation
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.Status

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListScreen(
    characterListState: CharacterListViewModel.CharacterListState,
    eventHandler: (CharacterListViewModel.CharacterListEvent) -> Unit,
    navController: NavHostController
) {
    LaunchedEffect(key1 = characterListState) {
        if (characterListState.status == Status.INIT) {
            eventHandler(CharacterListViewModel.CharacterListEvent.Init)
        }
    }
    if (characterListState.status == Status.LOADING) {
        DisplayLoadingScreen("")

    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            items(characterListState.characters) { item ->
                CharacterItem(item) {
                    navController.navigate(CharacterDetailNavigation.characterDetail(it.characterId).destination)
                }
            }
        }
    }


}

@ExperimentalMaterialApi
@Composable
fun CharacterItem(marvelCharacterUi: MarvelCharacterUi, onClick: (MarvelCharacterUi) -> Unit) {
    val painter =
        rememberImagePainter(data = marvelCharacterUi.url,
            builder = {
                transformations(
                )
            })
    Card(
        elevation = 8.dp,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        onClick = { onClick(marvelCharacterUi) }
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painter,
                contentDescription = stringResource(
                    id = R.string.marvel_hero_img
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(80.dp)

            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = marvelCharacterUi.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(
                        id = R.string.modified_date,
                        marvelCharacterUi.modifiedDate
                    ),
                    style = MaterialTheme.typography.body2,
                )
                if (marvelCharacterUi.description.isNotEmpty()) {
                    Text(
                        text = marvelCharacterUi.description,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun CharacterItemPreview() {
    CharacterItem(
        marvelCharacterUi = MarvelCharacterUi(
            "Hulk",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
            "normal date",
            description = "Just a description",
            characterId = 1,
            comics = emptyList(),
            series = emptyList(),
            stories = emptyList()
        ), {}
    )
}