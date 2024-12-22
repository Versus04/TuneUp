package com.example.tuneup.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuneup.model.Data
import com.example.tuneup.model.SearchDTO
import com.example.tuneup.model.SongDTO
import com.example.tuneup.model.searchResult
import com.example.tuneup.network.MusicApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class musicViewModel : ViewModel()
{
    private val _SongDto = MutableStateFlow<List<Data>>(emptyList())
    val songDto : StateFlow<List<Data>> = _SongDto
    private var _searchSong = MutableStateFlow("")
    var searchSong =_searchSong.asStateFlow()


    private val _searchResult = MutableStateFlow<List<searchResult>>(emptyList())
    val searchResult : StateFlow<List<searchResult>> = _searchResult
    fun getSong()
    {
        viewModelScope.launch()
        {
            try {
                val responses = MusicApiService.retrofitService.getsongs("3IoDK8qI")
                if (responses.isSuccessful)
                {
                    responses.body()?.let { musicresponse ->
                        _SongDto.value=musicresponse.data

                    }
                }
            }
            catch (e : Exception)
            {
                Log.d("lol" , e.toString())
            }
        }
    }
    fun searchSongs(searchResult: String)
    {
        viewModelScope.launch()
        {
            try {
                val searchResponses = MusicApiService.retrofitService.searchSong(searchResult)
                searchResponses.body()?.let {search->
                    _searchResult.value = search.data.results
                }
            }
            catch (e: Exception)
            {
                Log.d("searchError", e.toString())
            }
        }
    }
    fun updateSearch(newValue : String)
    {
        _searchSong.value=newValue

    }

}