package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository

class RefreshAsteroidWork(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val asteroidRepository = AsteroidRepository(database)

        return try {
            asteroidRepository.refreshAsteroids()
            Result.success()
        } catch (ex: Throwable) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "refresh_asteroids"
    }
}