package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.asDatabaseModel
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.database.model.asDomainModel
import com.udacity.asteroidradar.util.extension.toJSONObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val radarDatabase: AsteroidRadarDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(
            radarDatabase.asteroidDao.getAllAsteroidsByApproachingDate()
        ) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val nextSevenDays = getNextSevenDaysFormattedDates()
            NasaApi.retrofitService.getAsteroids(
                startDate = nextSevenDays.first(),
                endDate = nextSevenDays.last(),
                apiKey = BuildConfig.API_KEY
            ).toJSONObject()
                ?.parseAsteroidsJsonResult()
                ?.let {
                    radarDatabase.asteroidDao.insertAll(*it.asDatabaseModel())
                }
        }
    }
}