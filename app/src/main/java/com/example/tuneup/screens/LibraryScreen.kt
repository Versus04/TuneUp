package com.example.tuneup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tuneup.viewmodels.LibraryViewModel

@Composable
fun libraryScreen( libraryViewModel: LibraryViewModel, modifier: Modifier)
{
    Column(modifier){Text("Library Page")  }

}