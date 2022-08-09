package com.udacity.asteroidradar.data.api

object ApiConstants {
    const val BASE_ENDPOINT: String = "https://api.nasa.gov/"
    const val NEO_FEED_ENDPOINT: String = "neo/rest/v1/feed"
    const val PLANETARY_ENDPOINT: String = "planetary/apod"
}

object ApiParameters {
    const val API_KEY = "api_key"
    const val START_DATE = "start_date"
    const val END_DATE = "end_date"
}