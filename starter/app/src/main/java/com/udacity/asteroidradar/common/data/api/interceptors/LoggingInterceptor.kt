package com.udacity.asteroidradar.common.data.api.interceptors

import com.udacity.asteroidradar.common.utils.Logger
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Logger.i(message)
    }
}
