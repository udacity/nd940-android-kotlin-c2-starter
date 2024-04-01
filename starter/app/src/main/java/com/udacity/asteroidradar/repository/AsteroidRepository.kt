package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.DEFAULT_END_DATE_DAYS
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.api.NasaPlanetaryApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.toDatabaseString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.Calendar
import java.util.Date

class AsteroidRepository(private val database: AsteroidDatabase) {
    fun getAsteroidsInNext7Days(): LiveData<List<Asteroid>>{
        val calendar: Calendar = Calendar.getInstance()
        val now = calendar.time
        calendar.add(Calendar.DATE, DEFAULT_END_DATE_DAYS)
        val next7DaysDate = calendar.time
        return database.asteroidDao.getAsteroidsFromDateToDate(fromDate = now.toDatabaseString(), toDate = next7DaysDate.toDatabaseString()).map {
            it.asDomainModel()
        }
    }

    fun getAsteroidsOfToday(): LiveData<List<Asteroid>>{
        val calendar: Calendar = Calendar.getInstance()
        val today = calendar.time

        return database.asteroidDao.getAsteroidsOfDate(today.toDatabaseString()).map {
            it.asDomainModel()
        }
    }

    fun getSavedAsteroids(): LiveData<List<Asteroid>>{
        return database.asteroidDao.getAllAsteroids().map {
            it.asDomainModel()
        }
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val resultString = NasaPlanetaryApi.NasaApi.retrofitService.getAsteroidList(Constants.API_KEY)
            val asteroidList = parseAsteroidsJsonResult(JSONObject(resultString))
            database.asteroidDao.insertAll(asteroidList.asDatabaseModel())
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay {
        var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = NasaPlanetaryApi.NasaApi.retrofitService.getPictureOfDay(Constants.API_KEY)
        }
        return pictureOfDay
    }
}