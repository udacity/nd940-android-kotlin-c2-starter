package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.Network.api
import com.udacity.asteroidradar.network.model.ImageOfDayResponse
import com.udacity.asteroidradar.network.model.NeoFeedResponse
import com.udacity.asteroidradar.utile.Constants.API_QUERY_DATE_FORMAT
import com.udacity.asteroidradar.utile.Constants.DEFAULT_END_DATE_DAYS
import com.udacity.asteroidradar.utile.Constants.apiKey
import com.udacity.asteroidradar.utile.parseAsteroidsJsonResult
import org.json.JSONObject
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
            Log.d(this::class.simpleName, "getImageOfTheDay: response $response")
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun refreshAsteroids(): List<Asteroid>? {
        return try {
            calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_END_DATE_DAYS)
            val startDate = dateFormat.format(calendar.time)
            val endDate = dateFormat.format(calendar.time)

            val asteroids = api.getNeoFeed(startDate, endDate, apiKey)
            Log.d(this::class.simpleName, "refreshAsteroids: asteroids = $asteroids")

            val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroids))
            Log.d(this::class.simpleName, "refreshAsteroids: parsedAsteroids = $parsedAsteroids")

            parsedAsteroids
        } catch (e: Exception) {
            Log.e(this::class.simpleName, "refreshAsteroids: Exception = ${e.stackTrace}")
            null
        }
    }

}