package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.utils.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}