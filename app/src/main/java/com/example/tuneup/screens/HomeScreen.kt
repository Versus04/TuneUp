package com.example.tuneup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.tuneup.utility.AudioPlayer
import com.example.tuneup.utility.musicItems.MiniPlayer
import com.example.tuneup.viewmodels.LibraryViewModel
import com.example.tuneup.viewmodels.musicViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: musicViewModel, libraryViewModel: LibraryViewModel , modifier: Modifier)
{
    val navController = rememberNavController()
    var selectedScreen = remember { mutableIntStateOf(0) }
    val screenList = listOf<Screens>(Screens.HomeScreen,Screens.SearchPage,Screens.Library, Screens.Settings)
    val currentSong = viewModel.currentSong.collectAsState()
    val x = currentSong.value
    val playingvalue = viewModel.isPlaying.collectAsState()
    Box()
    {
    Scaffold(bottomBar ={
        Column() {
            if (currentSong != viewModel.emptySearchResult) {
                MiniPlayer(
                    viewModel.audioPlayer,
                    songName = currentSong.value.name,
                    artistName = currentSong.value.artists.primary.firstOrNull()?.name ?: "Unknown Artist",
                    imageUrl = currentSong.value.image.firstOrNull()?.url, Modifier,
                    navController,
                    { viewModel.updatePlaying() },
                    playingvalue.value
                )
            }

        NavigationBar { 
            screenList.forEachIndexed { index , navigation ->
                NavigationBarItem(
                    selected = selectedScreen.intValue == index,
                    onClick = {
                        selectedScreen.intValue=index
                        navController.navigate(navigation.route)
                              },
                    icon = { Icon(imageVector = navigation.icon , contentDescription = null) },
                    enabled =true,
                    label = { Text(text =navigation.title) }
                )
            }
        }}
    } ) {
        paddingValues ->
        NavHost(navController = navController , startDestination = Screens.HomeScreen.route)
        {
            composable(Screens.HomeScreen.route)
            {
                tempo(musicViewModel = viewModel,modifier = Modifier.padding(paddingValues))
            }
            composable(Screens.SearchPage.route)
            {
                searchScreen( musicViewModel = viewModel,modifier ,navController)
            }
            composable(Screens.Library.route)
            {
                libraryScreen(libraryViewModel = libraryViewModel ,modifier)
            }
            composable(Screens.Settings.route)
            {
                settingScreen(modifier)
            }
            composable(Screens.FullMusicPlayer.route)
            {
                FullMusicPlayer(
                    currentSong.value, audioPlayer = viewModel.audioPlayer,
                    isPlaying =playingvalue.value,
                    onclick ={viewModel.updatePlaying()},
                )
            }
        }

    }}

}