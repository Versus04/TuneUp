package com.example.tuneup.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


import com.example.tuneup.model.searchResult
import com.example.tuneup.utility.AudioPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FullMusicPlayer(searchResult: searchResult , isPlaying : Boolean ,audioPlayer: AudioPlayer ,onclick: ()-> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant), contentAlignment = Alignment.Center ,) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val imageUrl = if(searchResult.image.size>3) searchResult.image[3].url
            else if (searchResult.image.size>1) searchResult.image[2].url
            else searchResult.image[0].url

            AsyncImage(
                model = imageUrl,
                contentDescription = "Album Art",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit,
                onError = { Log.e("FullMusicPlayer", "Error loading image: ${it.result.throwable}") },
                onLoading = {/*Show a loading Screen*/ },
                onSuccess = {  }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = searchResult.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = searchResult.artists.primary.firstOrNull()?.name ?: "Unknown Artist",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(8.dp))
            LinearDeterminateIndicator(audioPlayer)
            Row() {
                IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.SkipPrevious , contentDescription = null ,
                            Modifier.fillMaxSize())
                }
                IconButton(onClick = {
                    if (isPlaying)
                    {
                        onclick()
                        audioPlayer.pause()
                    }
                    else
                    {
                        onclick()
                        audioPlayer.resume()
                    }

                }) {
                    Icon(imageVector =if(isPlaying)Icons.Filled.Pause
                    else Icons.Filled.PlayCircleFilled
                        , contentDescription = null,Modifier.fillMaxSize())
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.SkipNext , contentDescription = null,Modifier.fillMaxSize())

                }
            }
        }
    }
}
@Composable
fun LinearDeterminateIndicator(audioPlayer: AudioPlayer) {
    val currentProgress = remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                val duration = audioPlayer.getDuration()
                val currentPosition = audioPlayer.getCurrentPosition()
                
                currentProgress.value = if (duration > 0) {
                    (currentPosition.toDouble() / duration.toDouble())
                        .toFloat()
                        .coerceIn(0f, 1f)
                } else {
                    0f
                }
                delay(100) // Update more frequently for smoother progress
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(formatTime(audioPlayer.getCurrentPosition()))
        LinearProgressIndicator(
            progress = currentProgress.value,
            modifier = Modifier
                .weight(1f)
                .height(4.dp),
        )
        Text(formatTime(audioPlayer.getDuration()))
    }
}

private fun formatTime(milliseconds: Long): String {
    val seconds = (milliseconds / 1000) % 60
    val minutes = (milliseconds / (1000 * 60)) % 60
    return String.format("%d:%02d", minutes, seconds)
}

