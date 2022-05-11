package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM ${Constants.ASTEROID_TABLE} ORDER BY closeApproachDate DESC")
    fun getAsteroids(): Flow<List<Asteroid>>

    @Query("SELECT * FROM ${Constants.ASTEROID_TABLE} WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate DESC")
    fun getAsteroidsByCloseApproachDate(
        startDate:String,
        endDate:String
    ): Flow<List<Asteroid>>

    @Query("DELETE FROM ${Constants.ASTEROID_TABLE} WHERE closeApproachDate < :today")
    fun deletePreviousDayAsteroids(today: String): Int

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg asteroids: Asteroid)
}