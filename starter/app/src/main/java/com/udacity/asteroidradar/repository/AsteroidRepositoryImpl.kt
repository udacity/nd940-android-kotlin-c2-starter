package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject

class AsteroidRepositoryImpl(private val database: AsteroidDatabase) : AsteroidRepository {
    override suspend fun refreshAsteroids(startDate: String, endDate: String) =
        withContext(Dispatchers.IO) {
            val asteroidResponseBody: ResponseBody = Network.service.getAsteriods(
                startDate, endDate
            )
                .await()
            val asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody.string()))
            database.asteroidDao.insertAll(*asteroidList.asDomainModel())
        }

    suspend fun getPictureOfDay(): PictureOfDay? {
        var pictureOfDay: PictureOfDay = withContext(Dispatchers.IO) {
            Network.service.getPictureOfDay().await()
        }
        if (pictureOfDay.mediaType == Constants.IMAGE_MEDIA_TYPE) {
            return pictureOfDay
        }
        return null
    }
}