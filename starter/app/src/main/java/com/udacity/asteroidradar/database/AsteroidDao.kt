package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM asteroid ORDER BY closeApproachDate ASC")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM asteroid WHERE closeApproachDate= :date ORDER BY codename ASC")
    fun getAsteroidsOfDate(date: String): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM asteroid WHERE closeApproachDate>= :fromDate AND closeApproachDate<= :toDate ORDER BY closeApproachDate ASC")
    fun getAsteroidsFromDateToDate(fromDate: String, toDate: String): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<DatabaseAsteroid>)
}