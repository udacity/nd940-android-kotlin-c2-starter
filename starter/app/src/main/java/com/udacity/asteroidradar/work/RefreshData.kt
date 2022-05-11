package com.udacity.asteroidradar.work

import android.app.Application
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepositoryImpl
import retrofit2.HttpException

class RefreshData(
    private val application: Application,
    private val params: WorkerParameters
) : CoroutineWorker(application, params) {
    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabase(application)
        val asteroidRepository = AsteroidRepositoryImpl(database)
        return try {
            asteroidRepository.refreshAsteroids()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}