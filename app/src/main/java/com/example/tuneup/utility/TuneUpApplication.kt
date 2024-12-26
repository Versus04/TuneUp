package com.example.tuneup.utility

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.tuneup.data.SongDatabase

class TuneUpApplication : Application() {
    companion object {
        lateinit var db : SongDatabase 
            private set
        lateinit var appContext: Context
            private set
        lateinit var  audioPlayer: AudioPlayer
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        db = Room.databaseBuilder(this, SongDatabase::class.java, "song-database").fallbackToDestructiveMigration().build()
        audioPlayer = AudioPlayer.getInstance()
    }
}