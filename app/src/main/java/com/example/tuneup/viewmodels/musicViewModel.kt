package com.example.tuneup.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuneup.model.Album
import com.example.tuneup.model.Artists
import com.example.tuneup.model.Data
import com.example.tuneup.model.DownloadUrl
import com.example.tuneup.model.ImageXX
import com.example.tuneup.model.Primary
import com.example.tuneup.model.SearchDTO
import com.example.tuneup.model.SongDTO
import com.example.tuneup.model.searchResult
import com.example.tuneup.network.MusicApiService
import com.example.tuneup.utility.AudioPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import com.example.tuneup.model.RoomEntity
import com.example.tuneup.utility.TuneUpApplication

class musicViewModel : ViewModel()
{
    private val db  = TuneUpApplication.db.songDao()
    private val _isplaying = MutableStateFlow<Boolean>(false)
    val isPlaying : StateFlow<Boolean> = _isplaying.asStateFlow()
    private val _SongDto = MutableStateFlow<List<Data>>(emptyList())
    val songDto : StateFlow<List<Data>> = _SongDto.asStateFlow()
    private var _searchSong = MutableStateFlow("")
    var searchSong =_searchSong.asStateFlow()
    val context = TuneUpApplication.appContext
    val audioPlayer = AudioPlayer(context)
    val emptySearchResult = searchResult(
        album = Album(
            id = "31405540",
            name = "Teri Jogan",
            url = "https://www.jiosaavn.com/album/teri-jogan/vKfaGFFFVEg_"
        ),
        artists = Artists(
            primary = listOf(
                Primary(
                    id = "480392",
                    name = "Manjeera Ganguly",
                    role = "primary_artists",
                    type = "artist",
                    url = "https://www.jiosaavn.com/artist/manjeera-ganguly-songs/DHUYTNcuww8_",
                    image = listOf(
                        ImageXX(
                            quality = "50x50",
                            url = "https://c.saavncdn.com/artists/Manjeera_Ganguly_20180718065723_50x50.jpg"
                        )
                    )
                )
            ),
            featured = emptyList(),
            all = emptyList()
        ),
        copyright = "℗ 2013 Rave Music",
        downloadUrl = listOf(
            DownloadUrl(
                quality = "320kbps",
                url = "https://aac.saavncdn.com/295/7f1862c1f082f1fe2405d6443ca9ebda_320.mp4"
            )
        ),
        duration = 295,
        explicitContent = false,
        hasLyrics = false,
        id = "aNHXb42R",
        image = listOf(
            ImageXX(
                quality = "50x50",
                url = "https://c.saavncdn.com/295/Teri-Jogan-Hindi-2013-20211209130606-50x50.jpg"
            )
        ),
        label = "Rave Music",
        language = "hindi",
        lyricsId = 111,
        name = "Main Banke Hawa",
        playCount = 15962,
        releaseDate = 111,
        type = "song",
        url = "https://www.jiosaavn.com/song/main-banke-hawa/ESYjaRYEBWE",
        year = "2013"
    )
    private val _currentSong = MutableStateFlow<searchResult>(emptySearchResult)
    val currentSong : StateFlow<searchResult> = _currentSong.asStateFlow()
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
    fun updatePlaying()
    {
        if(_isplaying.value)
        {
         _isplaying.value=false
        }
        else{
            _isplaying.value=true
        }
    }
    fun updatecurrentSong (searchResult: searchResult)
    {
        _currentSong.value=searchResult
        viewModelScope.launch()
        {
            db.insert(RoomEntity(searchResult.id))
        }
        Log.d("newsong" ,searchResult.name)
    }

}