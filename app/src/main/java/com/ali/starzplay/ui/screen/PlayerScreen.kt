package com.ali.starzplay.ui.screen

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import com.ali.starzplay.R
import com.ali.starzplay.core.domain.model.MediaItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.media3.common.MediaItem as ExoMediaItem
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.media3.common.Player
import com.ali.starzplay.core.data.remote.api.Constants

@Destination
@Composable
fun PlayerScreen(
    item: MediaItem,
    navigator: DestinationsNavigator,
) {
    // Force landscape mode
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context as? android.app.Activity
        val originalOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = originalOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
    BackHandler(onBack = {
        navigator.popBackStack()
    })
    var playVideo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (playVideo) {
            val exoPlayer = remember {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(ExoMediaItem.fromUri(item.videoUrl))
                    prepare()
                    playWhenReady = true
                }
            }
            var isBuffering by remember { mutableStateOf(true) }
            // Listen to ExoPlayer state
            DisposableEffect(exoPlayer) {
                val listener = object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        isBuffering = state == Player.STATE_BUFFERING || state == Player.STATE_IDLE
                    }
                }
                exoPlayer.addListener(listener)
                onDispose {
                    exoPlayer.removeListener(listener)
                    exoPlayer.release()
                }
            }
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        this.player = exoPlayer
                        this.useController = true
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
            if (isBuffering) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        } else {
            val imageUrl = item.backdropPath?.let { "${Constants.TMDB_IMAGE_BASE_URL}$it" }
                ?: item.posterPath?.let { "${Constants.TMDB_IMAGE_BASE_URL}$it" }
            if (imageUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(stringResource(R.string.no_image), color = Color.White, style = MaterialTheme.typography.titleLarge)
            }
            IconButton(
                onClick = { playVideo = true },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = stringResource(R.string.play),
                    tint = Color.White,
                    modifier = Modifier.size(34.dp).background(Color.Black.copy(alpha = 0.5f))
                )
            }
        }
    }
} 