package com.example.tuneup.model

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