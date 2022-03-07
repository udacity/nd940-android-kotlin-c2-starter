package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import kotlin.collections.List

@Dao
interface AsteroidDatabaseDao {
    @Insert
    fun insertAll(vararg asteroids: AsteroidModel)

    @Delete
    fun delete(asteroid: AsteroidModel)

    @Query("SELECT * FROM asteroid_database")
    fun getAll(): List<AsteroidModel>
}
