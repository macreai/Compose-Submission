package com.communicare.genshinindex.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.communicare.genshinindex.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://media.licdn.com/dms/image/D5603AQFo6JwPe0z1kg/profile-displayphoto-shrink_200_200/0/1678626120260?e=1689811200&v=beta&t=H1JtnMoBkZYO6ZbNz6wbbEnNc6jBCQD0veiEQMapsQg",
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.name),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
        Text(
            text = stringResource(R.string.email),
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )
    }
}
