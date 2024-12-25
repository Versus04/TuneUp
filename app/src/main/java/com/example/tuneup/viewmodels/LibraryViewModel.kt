package com.example.tuneup.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuneup.model.Data
import com.example.tuneup.model.RoomEntity
import com.example.tuneup.network.MusicApiService
import com.example.tuneup.utility.TuneUpApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {
    private val db = TuneUpApplication.db
    private val dao = db.songDao()
    private val _ids = MutableStateFlow< List<RoomEntity>>(emptyList())
    val ids : StateFlow<List<RoomEntity>> = _ids
    private val _SongDto = MutableStateFlow<List<Data>>(emptyList())
    val songDto : StateFlow<List<Data>> = _SongDto
    fun getLastSession()
    {
        viewModelScope.launch()
        {
            dao.getlastSession().collect{
                entities ->
                _ids.value=entities

            }

        }
    }
    fun getSong()
    {
        viewModelScope.launch()
        {
            try {
                val newSongList = mutableListOf<Data>()
                ids.value.forEach { songId ->
                    val responses = MusicApiService.retrofitService.getsongs(songId.id.toString())
                    if (responses.isSuccessful) {
                        responses.body()?.let { musicresponse ->
                            newSongList.addAll(musicresponse.data)
                            Log.d("entitys", musicresponse.data.toString())
                        }
                    }
                }
                _SongDto.value = newSongList
            } catch (e: Exception) {
                Log.e("LibraryViewModel", "Error fetching songs", e)
            }
        }
    }
    init {
        viewModelScope.launch {
            getLastSession()
            // Wait for ids to be populated
            ids.collect { entities ->
                if (entities.isNotEmpty()) {
                    getSong()
                }
            }
        }
    }
}
