package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM asteroid")
    fun getAsteroids(): List<DatabaseAsteroid>
}