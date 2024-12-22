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
    Column(modifier) {
    songData.forEach { songs ->
        val songdata = songs.downloadUrl[4]
        Button(onClick = {

                audioPlayer.playAudio(songdata.url)
                Log.d("lol",songdata.url )
        }) {
            Text("Play Audio")
        }
        Spacer(Modifier.height(25.dp))
    }}


}