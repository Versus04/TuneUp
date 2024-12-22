package com.example.tuneup.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuneup.model.Data
import com.example.tuneup.model.SongDTO
import com.example.tuneup.network.MusicApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class musicViewModel : ViewModel()
{
    private val _SongDto = MutableStateFlow<List<Data>>(emptyList())
    val songDto : StateFlow<List<Data>> = _SongDto


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

    init {
        getSong()
    }
}