package com.example.tuneup.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route : String , val title : String , val icon : ImageVector) {
    object HomeScreen : Screens("home","Home" , Icons.Default.Home)
    object Settings : Screens("settings","Settings", Icons.Default.Settings)
    object Library : Screens("library","Library", Icons.Default.PlayArrow)
    object SearchPage : Screens("searchPage","Search", Icons.Default.Search)

}