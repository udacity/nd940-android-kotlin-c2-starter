package com.udacity.asteroidradar.main.domain.usecases

import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import com.udacity.asteroidradar.common.domain.Repository
import javax.inject.Inject

class RequestAsteroidUseCase@Inject constructor(private val  repository: Repository) {

    suspend operator fun invoke ()  {

        val asteroids = repository
            .requestAsteroid()
            .map { CachedAsteroid.fromDomain(it) }

        repository.deletePreviousDayAsteroidData()

        repository.storeAsteroids(asteroids)
    }

}
