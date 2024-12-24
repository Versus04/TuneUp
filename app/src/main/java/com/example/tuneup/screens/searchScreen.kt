package com.example.tuneup.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.utility.musicItems.songCard
import com.example.tuneup.viewmodels.musicViewModel


@Composable
fun searchScreen(musicViewModel: musicViewModel , modifier: Modifier , navController: NavController)
{
    val songData by musicViewModel.songDto.collectAsStateWithLifecycle()
    val searchs by musicViewModel.searchResult.collectAsState()
    val searchsong by musicViewModel.searchSong.collectAsState()
    //searchBar( searchsong, musicViewModel)


    Column(modifier.padding(8.dp)) {
        Row(modifier.padding(start = 8.dp , end = 8.dp) , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly)
        {
            OutlinedTextField(label = { Text("Search Song") },
                value = searchsong, onValueChange = {
                    musicViewModel.updateSearch(it) }, modifier = modifier.weight(1f),
                trailingIcon = {
                    IconButton(onClick = {
                        musicViewModel.searchSongs(searchsong)
                    }) {

                        Icon( imageVector = Icons.Filled.Search, contentDescription = null ,
                            Modifier
                                .clip(
                                    RoundedCornerShape(16.dp)
                                )
                                .height(32.dp)
                                .width(32.dp))
                    }
                })


        }

        LazyColumn() {
            items(searchs)
            {y->

                Log.d("download" ,y.downloadUrl[4].url)
                songCard(y  , audioPlayer = musicViewModel.audioPlayer , musicViewModel ,
                    { musicViewModel.updatePlaying() },navController)
            }
        }}

}