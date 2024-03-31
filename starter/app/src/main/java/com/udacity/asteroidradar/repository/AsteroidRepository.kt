package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaPlanetaryApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.dateToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroidsFromDate(dateToString(System.currentTimeMillis())).map {
        it.asDomainModel()
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val resultString = NasaPlanetaryApi.NasaApi.retrofitService.getAsteroidList(Constants.API_KEY)
            val asteroidList = parseAsteroidsJsonResult(JSONObject(resultString))
            database.asteroidDao.insertAll(asteroidList.asDatabaseModel())
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay{
        var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = NasaPlanetaryApi.NasaApi.retrofitService.getPictureOfDay(Constants.API_KEY)
        }
        return pictureOfDay
    }
}