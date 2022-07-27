package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.db.entities.AsteroidTable
import com.udacity.asteroidradar.domain.Asteroid

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroid_table")
    fun getAllAsteroids() : LiveData<List<AsteroidTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroids(vararg asteroids: AsteroidTable)

}