package com.example.tuneup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.tuneup.viewmodels.LibraryViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tuneup.utility.TuneUpApplication

@Composable
fun libraryScreen(libraryViewModel: LibraryViewModel) {
    val playlist by libraryViewModel.playlist.collectAsState()
    LazyColumn() {
        items(playlist)
        {x->
            Text(x.songsIds)
        }
    }


}