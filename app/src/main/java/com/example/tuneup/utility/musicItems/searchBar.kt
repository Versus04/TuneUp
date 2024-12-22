package com.example.tuneup.utility.musicItems

import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun searchBar(search : String)
{
    val fieldvalue = remember { mutableStateOf(search) }

}
@Preview
@Composable
fun prevsearchBar()
{
}
