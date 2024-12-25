package com.example.tuneup.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tuneup.model.RoomEntity

@Database(entities = [RoomEntity::class] , version = 1)
abstract class SongDatabase : RoomDatabase()  {
    abstract fun songDao() :  SongDao
}