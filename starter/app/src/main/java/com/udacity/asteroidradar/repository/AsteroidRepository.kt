package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.Network.api
import com.udacity.asteroidradar.network.model.ImageOfDayResponse
import com.udacity.asteroidradar.utile.Constants.API_QUERY_DATE_FORMAT
import com.udacity.asteroidradar.utile.Constants.DEFAULT_END_DATE_DAYS
import com.udacity.asteroidradar.utile.Constants.apiKey
import com.udacity.asteroidradar.utile.asDatabaseModel
import com.udacity.asteroidradar.utile.parseAsteroidsJsonResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRepository(
    private val database: AsteroidDatabase
) {

    private val calendar by lazy { Calendar.getInstance() }
    private val dateFormat by lazy { SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault()) }

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map((database.asteroidDao.getAllAsteroids())) {
            it.map { asteroid -> asteroid.asDomainModel() }
        }


    suspend fun getImageOfTheDay(): ImageOfDayResponse? {
        return try {
            val response = api.nasaImageOfTheDay(apiKey)
            Timber.d("ImageOfDayResponse: $response")
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun refreshAsteroids() = CoroutineScope(Dispatchers.Main).launch {
        withContext(Dispatchers.IO) {
            try {
                val startDate = dateFormat.format(calendar.time)
                calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_END_DATE_DAYS)
                val endDate = dateFormat.format(calendar.time)
                val asteroidsResponseBody = api.getNeoFeedAsync(startDate, endDate, apiKey).await()
                val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroidsResponseBody))
                database.asteroidDao.insertAsteroids(*parsedAsteroids.asDatabaseModel())
            } catch (e: Exception) {
                Timber.e("exception: $e")
                null
            }
        }
    }

}