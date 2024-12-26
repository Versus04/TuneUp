package com.example.tuneup.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tuneup.model.Playlist
import com.example.tuneup.model.RoomEntity

@Database(entities = [RoomEntity::class , Playlist::class] , version = 2)
abstract class SongDatabase : RoomDatabase()  {
    abstract fun songDao() :  SongDao
    abstract fun playlistDao() : playlistdao
}