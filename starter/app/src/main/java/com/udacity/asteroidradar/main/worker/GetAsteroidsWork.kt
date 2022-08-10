package com.udacity.asteroidradar.main.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.main.domain.usecases.GetAsteroidsUseCase
import com.udacity.asteroidradar.main.domain.usecases.RequestAsteroidUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetAsteroidsWork @Inject constructor(
    private val
    requestAsteroidUseCase : RequestAsteroidUseCase,
    @ApplicationContext appContext: Context,
    params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {


    companion object {
        const val WORK_NAME = "GetAsteroidsWork"
    }


    override suspend fun doWork(): Result {
        return try {
            requestAsteroidUseCase()
            Result.success()
        } catch (err: Exception) {
            Result.failure()
        }
    }
}