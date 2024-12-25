package com.example.tuneup.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lastPlayed")
data class RoomEntity
    (@PrimaryKey
     val id : String)