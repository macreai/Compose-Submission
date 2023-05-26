package com.communicare.genshinindex.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.communicare.genshinindex.R
import com.communicare.genshinindex.repo.CharacterRepository
import com.communicare.genshinindex.viewmodel.CharactersViewModel
import com.communicare.genshinindex.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    characterId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = viewModel(factory = ViewModelFactory(CharacterRepository())),
) {
    val character by viewModel.character.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.getCharacterById(characterId)
    }

    character?.let{ character ->
        Column(modifier = modifier) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Box {
                    AsyncImage(
                        model = character.photoProfileUrl,
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(400.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navigateBack() }
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = character.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = MaterialTheme.colors.primary,
                        fontSize = 32.sp
                    )
                    Text(
                        text = character.description,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                        fontSize = 24.sp
                    )

                }
            }
        }
    }

}