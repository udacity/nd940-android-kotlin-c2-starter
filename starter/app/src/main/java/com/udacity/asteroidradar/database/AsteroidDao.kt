package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AsteroidDao {

    @Insert
    suspend fun insert(asteroid: Asteroid)

    @Update
    suspend fun update(asteroid: Asteroid)

    @Query("SELECT * FROM asteroid_table WHERE id = :id")
    suspend fun getAsteroid(id: Long): Asteroid?

    @Query("DELETE FROM asteroid_table")
    suspend fun clear()

    /**
     * Selects and returns all the asteroids ordered by its date of approaching (more recent first).
     */
    @Query("SELECT * FROM asteroid_table ORDER BY close_approach_date ASC")
    fun getAllAsteroidsByApproachingDate()
}