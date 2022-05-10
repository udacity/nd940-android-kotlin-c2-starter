package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidsDatabaseDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(databaseAsteroid: ArrayList<DatabaseAsteroid>)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(asteroid: DatabaseAsteroid)

        @Query("SELECT * FROM asteroids_database LIMIT 10")
        suspend fun getAll(): List<DatabaseAsteroid>
//
//    @Query("SELECT * from asteroids_database WHERE id = :id")
//    fun getAsteroidById(id: Long): LiveData<DatabaseAsteroid>
}