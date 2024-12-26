package com.example.tuneup.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey
    val id :  Int,
    val name : String,
    val songsIds : String
)