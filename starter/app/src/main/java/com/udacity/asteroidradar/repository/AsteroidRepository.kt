package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.Asteroid

interface AsteroidRepository {
    suspend fun getAsteroidList():List<Asteroid>
}