package com.example.tuneup.screens

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.example.tuneup.viewmodels.musicViewModel


@Composable
fun tempo(musicViewModel: musicViewModel)
{
    val context : Context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    val mediaSession = MediaSession.Builder(context, player).build()
    val songdata = musicViewModel.songDto.collectAsState()
    songdata.value.forEach { songs->

    }

}