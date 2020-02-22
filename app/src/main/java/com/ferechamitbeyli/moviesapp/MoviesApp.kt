package com.ferechamitbeyli.moviesapp

import android.app.Application
import com.facebook.stetho.Stetho

class MoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}