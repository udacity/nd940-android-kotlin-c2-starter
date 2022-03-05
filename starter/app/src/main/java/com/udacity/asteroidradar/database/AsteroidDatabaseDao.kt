package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import kotlin.collections.List

@Dao
interface AsteroidDatabaseDao {
    @Insert
    fun insertAll(vararg asteroids: Asteroid)

    @Delete
    fun delete(asteroid: Asteroid)

    @Query("SELECT * FROM asteroid_database")
    fun getAll(): List<Asteroid>
}
