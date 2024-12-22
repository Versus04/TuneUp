package com.example.tuneup.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.viewmodels.musicViewModel


@Composable
fun tempo(musicViewModel: musicViewModel , modifier: Modifier)
{
    val context = LocalContext.current
    val songData by musicViewModel.songDto.collectAsStateWithLifecycle()
    val audioPlayer = remember { AudioPlayer(context) }
    val searchs by musicViewModel.searchResult.collectAsState()
    Column(modifier) {
        // Debug Text to verify screen rendering
        Text("Debug: Screen is rendering")
        
        // Debug Text to show data
        Text("Debug: Songs count: ${searchs.size}")

        if (songData.isEmpty()) {
            Text("Loading songs...")
            // Trigger initial load if needed
            LaunchedEffect(Unit) {
                musicViewModel.getSong()
            }
            return@Column
        }

        songData.forEach { songs ->
            Text("Found song") // Debug text
            songs.downloadUrl.getOrNull(4)?.let { songdata ->
                Button(onClick = {
                        musicViewModel.getSong()
                        audioPlayer.playAudio(songdata.url)
                        //musicViewModel.searchSongs("baarish")
                        Log.d("lol",songdata.url )
                }) {
                    if (searchs.isEmpty()) {
                        Text("Play Song")
                    } else {
                        searchs.forEach { s->
                            Text(s.artists.primary.toString())
                        }
                    }
                }
                Spacer(Modifier.height(25.dp))
            }
        }
    }


}