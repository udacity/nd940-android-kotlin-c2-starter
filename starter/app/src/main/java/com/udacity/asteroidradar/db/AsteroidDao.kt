package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.udacity.asteroidradar.db.entities.AsteroidTable

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroid_table")
    fun getAllAsteroids() : LiveData<List<AsteroidTable>>

}