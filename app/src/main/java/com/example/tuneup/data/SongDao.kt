package com.example.tuneup.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tuneup.model.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(entity: RoomEntity)
@Update
suspend fun  update(entity: RoomEntity)
@Delete
suspend fun  delete(entity: RoomEntity)

@Query("SELECT * FROM lastPlayed")
fun getlastSession() : Flow<List<RoomEntity>>

}