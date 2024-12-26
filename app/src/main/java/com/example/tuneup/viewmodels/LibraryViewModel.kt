package com.example.tuneup.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuneup.model.Data
import com.example.tuneup.model.Playlist
import com.example.tuneup.model.RoomEntity
import com.example.tuneup.network.MusicApiService
import com.example.tuneup.utility.TuneUpApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {
    val db = TuneUpApplication.db.playlistDao()
    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlist : StateFlow<List<Playlist>> = _playlists.asStateFlow()

    private fun loadPlaylists()
    {
        viewModelScope.launch()
        {
            try {
                db.getallPlayLists().collect(){play->
                    _playlists.value=play
                }
            }
            catch (e: Exception)
            {
                Log.d("playlist",e.toString())
            }
        }
    }
    init {
    loadPlaylists()
    }
    private fun deletePlaylist(playlist: Playlist)
    {
        viewModelScope.launch()
        {
            db.deletePlaylist(playlist.id)
        }
    }
}
