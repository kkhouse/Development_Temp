package com.example.resultmonadsample

import android.app.Application
import timber.log.Timber

class App : Application() {
    companion object {
        const val TIMBER_TAG = "TIMBER_TAG"
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}