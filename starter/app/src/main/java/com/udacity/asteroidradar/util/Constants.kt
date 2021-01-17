package com.udacity.asteroidradar.util

enum class LoadingStatus {
    LOADING, DONE
}

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov"
}