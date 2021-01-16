package com.udacity.asteroidradar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.database.model.DatabaseAsteroid

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("DELETE FROM asteroid_table")
    suspend fun clear()

    /**
     * Selects and returns all the asteroids ordered by its date of approaching (more recent first).
     */
    @Query("SELECT * FROM asteroid_table ORDER BY close_approach_date ASC")
    fun getAllAsteroidsByApproachingDate(): LiveData<List<DatabaseAsteroid>>
}