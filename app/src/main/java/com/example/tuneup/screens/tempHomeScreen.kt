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
fun Home(musicViewModel: musicViewModel ,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val lastSession by homeViewModel.songDto.collectAsState()
    val audioPlayer = TuneUpApplication.audioPlayer
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Bar

            // Welcome Section
            Text(
                "Welcome Back!", 
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Recently Played Section
            SectionTitle("Recently Played")
            if (lastSession.isEmpty()) {
                EmptyStateMessage("No recently played songs")
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(lastSession) { song ->
                        LibrarySongCard(song, audioPlayer ,modifier ,navController ,musicViewModel)
                    }
                }
            }

            // Most Played Section
            SectionTitle("Most Played")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: Replace with actual most played songs
                items(5) {
                    PlaceholderSongCard()
                }
            }

            // Recommended Section
            SectionTitle("Recommended for You")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: Replace with actual recommendations
                items(5) {
                    PlaceholderSongCard()
                }
            }

            // New Releases Section
            SectionTitle("New Releases")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: Replace with actual new releases
                items(5) {
                    PlaceholderSongCard()
                }
            }

            // Your Playlists Section
            SectionTitle("Your Playlists")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: Replace with actual playlists
                items(3) {
                    PlaylistCard()
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
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun EmptyStateMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
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
            .width(150.dp)
            .height(200.dp)
            .padding(4.dp)
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
            .width(200.dp)
            .height(120.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Search, // Replace with appropriate playlist icon
                contentDescription = "Playlist",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
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








