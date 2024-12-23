package com.example.tuneup.utility

import android.app.Application
import android.content.Context

class TuneUpApplication : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}