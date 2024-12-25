package com.example.tuneup.screens

import android.content.Context
import android.util.Log
import android.view.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.utility.TuneUpApplication
import com.example.tuneup.utility.musicItems.LibrarySongCard
import com.example.tuneup.utility.musicItems.searchBar
import com.example.tuneup.utility.musicItems.songCard
import com.example.tuneup.viewmodels.HomeViewModel
import com.example.tuneup.viewmodels.LibraryViewModel
import com.example.tuneup.viewmodels.musicViewModel

@Composable
fun Home(
    musicViewModel: musicViewModel,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val lastSession by homeViewModel.songDto.collectAsStateWithLifecycle()
    val audioPlayer = TuneUpApplication.audioPlayer

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        item {
            Text(
                "Welcome Back!",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Text(
                "Recently Played",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        item {
            if (lastSession.isEmpty()) {
                EmptyStateMessage("No recently played songs")
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    items(lastSession) { song ->
                        LibrarySongCard(
                            audioPlayer = audioPlayer,
                            modifier = Modifier,
                            navController = navController,
                            musicViewModel = musicViewModel,
                            searchResult = song
                        )
                    }
                }
            }
        }
        item {
            Column() {
                // Most Played Section
                SectionTitle("Most Played")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) { PlaceholderSongCard() }
                }

                // Recommended Section
                SectionTitle("Recommended for You")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) { PlaceholderSongCard() }
                }

                // New Releases Section
                SectionTitle("New Releases")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) { PlaceholderSongCard() }
                }

                // Playlists Section
                SectionTitle("Your Playlists")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(3) { PlaylistCard() }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun EmptyStateMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PlaceholderSongCard() {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(180.dp)
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun PlaylistCard() {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(100.dp)
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Playlist",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Playlist Name",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "0 songs",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}








