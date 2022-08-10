package com.udacity.asteroidradar.common.data.cache

import com.udacity.asteroidradar.common.data.cache.daos.AsteroidDao
import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import com.udacity.asteroidradar.common.utils.DateUtils.getNextSevenDaysFormattedDates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AsteroidCache @Inject constructor(
    private val asteroidDao: AsteroidDao
) : Cache {
    override suspend fun storeAsteroids(asteroids: List<CachedAsteroid>) {

        asteroidDao.insertAsteroids(asteroids)
    }

    override fun getAllAsteroids(): Flow<List<CachedAsteroid>> {

        return asteroidDao.getAllAsteroid()
    }

    override suspend fun deletePreviousDayAsteroidData() {

        val dates = getNextSevenDaysFormattedDates()
        dates.forEach {
            asteroidDao.deleteAsteroid(it)
        }
    }
}