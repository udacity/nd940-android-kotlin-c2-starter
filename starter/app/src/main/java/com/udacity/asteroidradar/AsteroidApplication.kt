package com.udacity.asteroidradar

import android.app.Application
import timber.log.Timber
import timber.log.Timber.Forest.plant


class AsteroidApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}