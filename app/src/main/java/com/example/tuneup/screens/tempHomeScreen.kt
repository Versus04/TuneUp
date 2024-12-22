package com.example.tuneup.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.tuneup.utility.musicItems.searchBar
import com.example.tuneup.utility.musicItems.songCard
import com.example.tuneup.viewmodels.musicViewModel


@Composable
fun tempo(musicViewModel: musicViewModel , modifier: Modifier)
{
    val songData by musicViewModel.songDto.collectAsStateWithLifecycle()
    val searchs by musicViewModel.searchResult.collectAsState()
    val searchsong by musicViewModel.searchSong.collectAsState()
    //searchBar( searchsong, musicViewModel)
    val context = LocalContext.current
    val audioPlayer = remember { AudioPlayer(context) }

    Column(modifier) {
    Row()
    {
        TextField(value = searchsong , onValueChange = { musicViewModel.updateSearch(it) })
        Button(onClick = { musicViewModel.searchSongs(searchsong)

        }) {Text("search songs") }
    }

    LazyColumn() {
        items(searchs)
        {y->
            Log.d("download" ,y.downloadUrl[4].url)
            songCard(y  , audioPlayer = audioPlayer)
        }
    }}
        }








