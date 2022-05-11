package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.api.getSeventhDay
import com.udacity.asteroidradar.api.getToday

interface AsteroidRepository {
    suspend fun refreshAsteroids(startDate: String = getToday(), endDate: String = getSeventhDay())

    suspend fun deletePreviousDayAsteroids()
}