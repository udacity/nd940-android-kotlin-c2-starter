package com.udacity.asteroidradar.work

import android.app.Application
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepositoryImpl

class DeleteData(private val application: Application, private val params: WorkerParameters) :
    CoroutineWorker(application, params) {
    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabase(application)
        val asteroidRepository = AsteroidRepositoryImpl(database)
        return try {
            asteroidRepository.deletePreviousDayAsteroids()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}