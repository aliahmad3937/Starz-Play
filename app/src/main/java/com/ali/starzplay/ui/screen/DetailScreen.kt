package com.ali.starzplay.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ali.starzplay.R
import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.model.MediaType
import com.ali.starzplay.ui.screen.destinations.PlayerScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.foundation.rememberScrollState

@Destination
@Composable
fun DetailScreen(
    item: MediaItem,
    navigator: DestinationsNavigator
) {
//    val item = viewModel.item
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp , vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = item.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" }
            ?: item.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
        if (imageUrl != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No Image", color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(item.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(item.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        if (item.mediaType == MediaType.MOVIE.name.lowercase() || item.mediaType == MediaType.TV.name.lowercase()) {
            Button(onClick = {
                navigator.navigate(PlayerScreenDestination(item = item))
            }) {
                Text(stringResource(R.string.play))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navigator.popBackStack()
        }) {
            Text(stringResource(R.string.back))
        }
    }
} 