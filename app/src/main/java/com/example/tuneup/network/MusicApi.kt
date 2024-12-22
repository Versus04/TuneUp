package com.example.tuneup.network

import com.example.tuneup.model.SongDTO

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Url

private const val BASE_URL = "https://saavn.dev/api/"

interface MusicApi {

    @GET("songs/{songId}")
    suspend fun getsongs(@Path("songId") songID: String) : Response<SongDTO>



}
private val retrofit : Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

object MusicApiService
{
    val retrofitService : MusicApi by lazy {
        retrofit.create(MusicApi::class.java)
    }
}