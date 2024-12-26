package com.example.tuneup.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tuneup.model.Playlist
import kotlinx.coroutines.flow.Flow

@Dao
interface playlistdao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPlayList(playlist: Playlist)
    @Query("DELETE FROM playlists WHERE id=:playlistid")
    suspend fun deletePlaylist(playlistid: Int)
    @Query("SELECT * FROM playlists")
     fun getallPlayLists() : Flow<List<Playlist>>
}