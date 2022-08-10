package com.udacity.asteroidradar.common.domain

import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import com.udacity.asteroidradar.common.domain.model.Asteroid
import com.udacity.asteroidradar.common.domain.model.PictureOfDay
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun requestAsteroid() : List<Asteroid>

    suspend fun requestPictureOfDay() : PictureOfDay

    suspend fun storeAsteroids(asteroids: List<CachedAsteroid>)

    fun getAllAsteroids(): Flow<List<Asteroid>>

    suspend fun deletePreviousDayAsteroidData()

}