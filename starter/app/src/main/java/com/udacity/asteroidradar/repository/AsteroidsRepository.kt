package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabaseDao
import com.udacity.asteroidradar.database.DatabaseAsteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidsRepository(private val asteroidDao: AsteroidsDatabaseDao) {

    suspend fun refreshAsteroidsList() {
        withContext(Dispatchers.IO){
            var asteroidsList = AsteroidApi.retrofitService.getAsteroids()
            val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroidsList))
            if(!parsedAsteroids.isNullOrEmpty()){
                for(asteroid in parsedAsteroids)
                    insertAsteroid(asteroid)
            }
        }
    }

    private fun convertAsteroid(it: Asteroid) : DatabaseAsteroid {
        return DatabaseAsteroid(
            id = it.id,
            codename =it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }

    fun insertAsteroid(asteroid: Asteroid){
        val asteroidD = convertAsteroid(asteroid)
        asteroidDao.insert(asteroidD)
    }

    fun insertAll(asteroids: ArrayList<DatabaseAsteroid>){
        asteroidDao.insertAll(asteroids)
    }

//    suspend fun getAllAsteroids(): ArrayList<DatabaseAsteroid> = asteroidDao.getAsteroidsList()

}