package com.example.tuneup.utility.musicItems

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tuneup.model.Data
import com.example.tuneup.model.searchResult
import com.example.tuneup.screens.Screens
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.viewmodels.musicViewModel

@Composable
fun LibrarySongCard(
    searchResult: Data,
    audioPlayer: AudioPlayer,
    modifier: Modifier = Modifier,
    navController: NavController,
   musicViewModel: musicViewModel
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(200.dp)
            .padding(4.dp)
            .clickable {
                audioPlayer.playAudio(searchResult.downloadUrl[4].url)
                navController.navigate(Screens.FullMusicPlayer.route)
                 musicViewModel.updatecurrentSong(searchResult.toSearchResult())
                musicViewModel.updatePlaying()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(searchResult.image[2].url)
                    .crossfade(true)
                    .build(),
                contentDescription = "Song Artwork",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,

            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 100f
                        )
                    )
            )

            // Song information
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = searchResult.name ?: "Unknown",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                /*Text(
                    text = searchResult.artists.primary[1] ?: "Unknown Artist",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) */
            }



        }
    }
}


