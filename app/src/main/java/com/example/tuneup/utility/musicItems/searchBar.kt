package com.example.tuneup.utility.musicItems

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.tuneup.viewmodels.musicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(search : String , viewModel: musicViewModel)
{
    val fieldvalue = remember { mutableStateOf(search) }
    SearchBar(
        query = fieldvalue.value,
        onQueryChange = { fieldvalue.value = it },
        onSearch = { viewModel.searchSongs(fieldvalue.value) },
        active = TODO(),
        onActiveChange = TODO(),
        modifier = TODO(),
        enabled = TODO(),
        placeholder = TODO(),
        leadingIcon = TODO(),
        trailingIcon = TODO(),
        shape = TODO(),
        colors = TODO(),
        tonalElevation = TODO(),
        shadowElevation = TODO(),
        windowInsets = TODO(),
        interactionSource = TODO(),
        content = TODO()
    )
}
@Preview
@Composable
fun prevsearchBar()
{
}
