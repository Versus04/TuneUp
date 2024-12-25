package com.example.tuneup.model

import android.app.appsearch.SearchResult
import android.util.Log

data class Data(
    val album: Album,
    val artists: Artists,
    val copyright: String,
    val downloadUrl: List<DownloadUrl>,
    val duration: Int,
    val explicitContent: Boolean,
    val hasLyrics: Boolean,
    val id: String,
    val image: List<ImageXX>,
    val label: String,
    val language: String,
    val lyricsId: Any,
    val name: String,
    val playCount: Int,
    val releaseDate: String,
    val type: String,
    val url: String,
    val year: String
)
{
    fun toSearchResult(): searchResult = try {
        searchResult(
            album = album,
            artists = artists,
            copyright = copyright,
            downloadUrl = downloadUrl,
            duration = duration,
            explicitContent = explicitContent,
            hasLyrics = hasLyrics,
            id = id,
            image = image,
            label = label,
            language = language,
            lyricsId = lyricsId ?: "",
            name = name,
            playCount = playCount,
            releaseDate = releaseDate, // Changed: don't cast, just pass directly
            type = type,
            url = url,
            year = year
        )
    } catch (e: Exception) {
        Log.e("bass", "Error converting to SearchResult: ${e.message}", e)
        throw e
    }
}