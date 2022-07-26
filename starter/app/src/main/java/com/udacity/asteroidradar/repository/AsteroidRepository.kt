package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.Network.api
import com.udacity.asteroidradar.network.model.ImageOfDayResponse

class AsteroidRepository(
    private val database: AsteroidDatabase
) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map((database.asteroidDao.getAllAsteroids())) {
            it.map { asteroid -> asteroid.asDomainModel() }
        }


    suspend fun getImageOfTheDay(): ImageOfDayResponse? {
        Log.d(this::class.simpleName, "getImageOfTheDay: API_KEY = $apiKey")
        return try {
            val response = api.nasaImageOfTheDay(apiKey)
            Log.d(this::class.simpleName, "getImageOfTheDay: response $response")
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private companion object {
        private const val apiKey = BuildConfig.NASA_API_KEY
    }
}