package com.communicare.genshinindex.ui.screen

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.communicare.genshinindex.repo.CharacterRepository
import com.communicare.genshinindex.ui.components.CharacterListItem
import com.communicare.genshinindex.ui.components.ScrollToTopButton
import com.communicare.genshinindex.ui.components.SearchBar
import com.communicare.genshinindex.viewmodel.CharactersViewModel
import com.communicare.genshinindex.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = viewModel(factory = ViewModelFactory(CharacterRepository())),
    navigateToDetail: (Int) -> Unit
) {
    val groupedCharacter by viewModel.groupedCharacter.collectAsState()
    val query by viewModel.query
    val context = LocalContext.current

    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                )
            }
            groupedCharacter.forEach { (weapon, characters) ->
                stickyHeader {
                    CharacterHeader(
                        weapon
                    )
                }
                items(characters, key = {it.id}) { character ->
                    CharacterListItem(
                        name = character.name,
                        photoProfileUrl = character.photoProfileUrl,
                        modifier = Modifier
                            .clickable {
                                navigateToDetail(character.id)
                                Toast
                                    .makeText(context, "Hi, ${character.name}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .fillMaxWidth()

                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Composable
fun CharacterHeader(
    weapon: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        Text(
            text = weapon,
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


