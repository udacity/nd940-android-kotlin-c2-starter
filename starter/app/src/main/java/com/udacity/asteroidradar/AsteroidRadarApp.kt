package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.work.AsteroidRadarWorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AsteroidRadarApp : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch { AsteroidRadarWorkManager.setupDailyAsteroidsWork() }
    }
}