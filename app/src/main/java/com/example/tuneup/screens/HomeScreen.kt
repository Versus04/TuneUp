package com.example.tuneup.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuneup.viewmodels.musicViewModel

@Composable
fun MainScreen(viewModel: musicViewModel , modifier: Modifier)
{
    val navController = rememberNavController()
    var selectedScreen = remember { mutableIntStateOf(0) }
    val screenList = listOf<Screens>(Screens.HomeScreen,Screens.SearchPage,Screens.Library, Screens.Settings)
    Scaffold(bottomBar ={
        NavigationBar { 
            screenList.forEachIndexed { index , navi ->
                NavigationBarItem(
                    selected = selectedScreen.intValue == index,
                    onClick = {
                        selectedScreen.intValue=index
                        navController.navigate(navi.route)
                              },
                    icon = { Icon(imageVector = navi.icon , contentDescription = null) },
                    enabled =true,
                    label = { Text(text =navi.title) }
                )
            }
        }
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
                searchScreen(modifier)
            }
            composable(Screens.Library.route)
            {
                libraryScreen(modifier)
            }
            composable(Screens.Settings.route)
            {
                settingScreen(modifier)
            }
        }

    }
}