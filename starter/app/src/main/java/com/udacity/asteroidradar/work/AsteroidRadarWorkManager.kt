package com.udacity.asteroidradar.work

import android.os.Build
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object AsteroidRadarWorkManager {

    fun setupDailyAsteroidsWork() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }
            .build()

        val request = PeriodicWorkRequestBuilder<RefreshAsteroidWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshAsteroidWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}