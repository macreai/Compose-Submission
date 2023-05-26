package com.communicare.genshinindex.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.communicare.genshinindex.ui.theme.GenshinIndexTheme

@Composable
fun CharacterListItem(
    name: String,
    photoProfileUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        AsyncImage(
            model = photoProfileUrl,
            contentDescription = name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GenshinIndexListPreview() {
    GenshinIndexTheme {
    }
}