package com.example.tuneup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tuneup.screens.MainScreen
import com.example.tuneup.ui.theme.TuneUpTheme
import com.example.tuneup.viewmodels.HomeViewModel
import com.example.tuneup.viewmodels.LibraryViewModel
import com.example.tuneup.viewmodels.musicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val homeViewModel : HomeViewModel by viewModels()
        val viewModel : musicViewModel by viewModels()
        val libraryViewModel : LibraryViewModel by viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TuneUpTheme {
                MainScreen( homeViewModel,viewModel, modifier = Modifier.statusBarsPadding() , libraryViewModel = libraryViewModel)
            }
        }
    }
}

