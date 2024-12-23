package com.example.tuneup.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tuneup.model.searchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class sessionViewModels  : ViewModel() {
private val _lastplayed  = MutableStateFlow<List<searchResult>>(emptyList())
    val lastplayed : StateFlow<List<searchResult>> = _lastplayed

    fun addSong(searchResult: searchResult)
    {
        _lastplayed.update { currentlist->
            currentlist + searchResult
        }
    }
    fun getLastPlayed() = _lastplayed
}