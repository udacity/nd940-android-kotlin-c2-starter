package com.udacity.asteroidradar.utile

import com.udacity.asteroidradar.BuildConfig

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7

    // Database
    const val DATABASE_NAME = "asteroid_database.db"

    // Network
    const val BASE_URL = "https://api.nasa.gov/"

    const val apiKey = BuildConfig.NASA_API_KEY
    const val ASTEROID_ID = "asteroid_id"
    const val START_DATE = "start_date"
    const val END_DATE = "end_date"
    const val API_KEY = "api_key"

}