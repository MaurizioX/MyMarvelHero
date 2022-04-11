package mzx.mymarvel.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import mzx.mymarvel.R
import mzx.mymarvel.ui.model.MarvelCharacterUi
import mzx.mymarvel.ui.model.MarvelInfoUi
import mzx.mymarvel.ui.model.Status


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    state: CharacterDetailViewModel.CharacterDetailState,
    onEvent: (CharacterDetailViewModel.CharacterDetailEvent) -> Unit
) {
    LaunchedEffect(key1 = state) {
        if (state.status == Status.INIT) {
            onEvent(CharacterDetailViewModel.CharacterDetailEvent.Init(characterId))
        }
    }

    when (state.status) {
        Status.LOADING -> DisplayLoadingScreen(stringResource(id = R.string.loading_detail))
        Status.LOADED -> DisplayCharacterDetailScreen(
            state.character ?: throw IllegalArgumentException("Not possible Character")
        )
    }
}

@Composable
fun DisplayCharacterDetailScreen(marvelCharacterUi: MarvelCharacterUi) {

    var scrollState by remember { mutableStateOf(ScrollState.Saver) }
    val painter =
        rememberImagePainter(data = marvelCharacterUi.url,
            builder = {
                transformations(
                )
                listener(onSuccess = { _, _ -> })
            })
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(state = scrollState))
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp), content =
    {
        val modifierText = Modifier.padding(horizontal = 20.dp)
        item {
            Image(
                painter = painter,
                contentDescription = stringResource(
                    id = R.string.marvel_hero_img
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .height(200.dp)

            )
            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = marvelCharacterUi.name,
                style = MaterialTheme.typography.h4,
                modifier = modifierText
            )

            Spacer(modifier = Modifier.size(10.dp))
            marvelCharacterUi.description.let {
                if (it.isNotEmpty()) Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    modifier = modifierText
                )
            }

        }
        if (marvelCharacterUi.hasComics)
            item {
                DisplayCharacterInfo(
                    stringResource(id = R.string.comics),
                    marvelCharacterUi.comics,
                    modifierText
                )
            }
        if (marvelCharacterUi.hasSeries)
            item {
                DisplayCharacterInfo(
                    title = stringResource(id = R.string.series),
                    elements = marvelCharacterUi.series,
                    modifier = modifierText
                )
            }
        if (marvelCharacterUi.hasStories)
            item {
                DisplayCharacterInfo(
                    title = stringResource(id = R.string.stories),
                    elements = marvelCharacterUi.stories,
                    modifier = modifierText
                )
            }
    })

}

@Composable
private fun DisplayCharacterInfo(
    title: String,
    elements: List<MarvelInfoUi>,
    modifier: Modifier
) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = title,
        modifier = modifier,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.size(10.dp))
    elements.map {
        Text(
            text = " - ${it.name} ",
            style = MaterialTheme.typography.body2,
            modifier = modifier
        )
    }


}

@Composable
fun DisplayInfo(name: String) {

}

@Preview(showBackground = true, widthDp = 400, heightDp = 600)
@Composable
fun DisplayLoadingScreenPreview() {
    DisplayLoadingScreen("Screen title")
}

@Composable
fun DisplayLoadingScreen(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp, 60.dp))
    }


}