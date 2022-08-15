package com.udacity.asteroidradar.common.data

import com.udacity.asteroidradar.common.data.api.Api
import com.udacity.asteroidradar.common.data.api.model.ApiPictureOfDay
import com.udacity.asteroidradar.common.data.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.common.data.cache.Cache
import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import com.udacity.asteroidradar.common.domain.NetworkException
import com.udacity.asteroidradar.common.domain.Repository
import com.udacity.asteroidradar.common.domain.model.Asteroid
import com.udacity.asteroidradar.common.domain.model.PictureOfDay
import com.udacity.asteroidradar.common.utils.DateUtils.getCurrentDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cache: Cache, private val api: Api) :
    Repository {

    override suspend fun requestAsteroid(): List<Asteroid> {

        try {

            val jsonObject = api.fetchAsteroid(startDate = getCurrentDate(), endDate = "")


            return parseAsteroidsJsonResult(JSONObject(jsonObject))

        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }


    override suspend fun requestPictureOfDay(): PictureOfDay {

        try {
            return ApiPictureOfDay.mapToDomain(api.fetchPictureOfDay())

        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeAsteroids(asteroids: List<CachedAsteroid>) {

        cache.storeAsteroids(asteroids)
    }

    override fun getAllAsteroids(): Flow<List<Asteroid>> {
        return cache.getAllAsteroids()
            .distinctUntilChanged()
            .map { asteroidList -> asteroidList.map { it.toDomain() } }
    }

    override suspend fun deletePreviousDayAsteroidData() {
        cache.deletePreviousDayAsteroidData()
    }
}