package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.udacity.asteroidradar.common.utils.Logger
import com.udacity.asteroidradar.common.utils.createExceptionHandler
import com.udacity.asteroidradar.main.worker.GetAsteroidsWork
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class App : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        initLogger()


       val errorMessage = "Failed to lunch Worker "
       val exceptionHandler = applicationScope.createExceptionHandler(errorMessage) {  }
        applicationScope.launch (exceptionHandler){
            setupRecurringWork()
        }
    }

    private fun initLogger() {
        Logger.init()
    }



    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .apply {
               setRequiresDeviceIdle(true)
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<GetAsteroidsWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

       WorkManager.getInstance().enqueueUniquePeriodicWork(
            GetAsteroidsWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

    }
}