package com.example.tuneup.utility.musicItems

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.tuneup.model.searchResult
import com.example.tuneup.screens.Screens
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.viewmodels.musicViewModel


    @Composable
    fun LibrarySongCard(searchResult: searchResult  ,
                 audioPlayer: AudioPlayer,musicViewModel: musicViewModel, onclick : ()-> Unit ,navController: NavController
    )
    {

        Card(Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(enabled = true, onClick =
            {
                onclick()
                musicViewModel.updatecurrentSong(searchResult)
                navController.navigate(Screens.FullMusicPlayer.route)
                try {
                    audioPlayer.playAudio(searchResult.downloadUrl[4].url)
                } catch (e: Exception) {
                    Log.d("playingerror", e.toString())
                }
            })) {
            Column(Modifier.padding(8.dp) , verticalArrangement = Arrangement.Center) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly){

                    AsyncImage(model = searchResult.image[2].url ,
                        contentDescription = null ,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier
                            .height(56.dp)
                            .width(56.dp)
                            .clip(RoundedCornerShape(8.dp)
                            )
                    )
                    Spacer(Modifier.padding(8.dp))
                    Column {
                        Text(searchResult.name , maxLines = 1)
                        Text(searchResult.label , maxLines = 1)
                    }
                }

            }
        }
    }
