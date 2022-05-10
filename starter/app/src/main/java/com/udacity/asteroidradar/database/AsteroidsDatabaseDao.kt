package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidsDatabaseDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(databaseAsteroid: ArrayList<DatabaseAsteroid>)

        @Insert
        fun insert(asteroid: DatabaseAsteroid)

//        @Query("SELECT * FROM asteroids_database")
//        suspend fun getAll(asteroids: List<DatabaseAsteroid>)
//
//    @Query("SELECT * from asteroids_database WHERE id = :id")
//    fun getAsteroidById(id: Long): LiveData<DatabaseAsteroid>
}