package com.udacity.asteroidradar.common.data.cache

import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import kotlinx.coroutines.flow.Flow

interface Cache {

    suspend fun storeAsteroids(asteroids: List<CachedAsteroid>)

    fun getAllAsteroids(): Flow<List<CachedAsteroid>>

    suspend fun deletePreviousDayAsteroidData()
}