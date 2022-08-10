package com.udacity.asteroidradar.common.data.cache.daos

import androidx.room.*
import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(asteroid : List<CachedAsteroid>)


    @Transaction
    @Query("SELECT * FROM Asteroids ORDER BY id DESC")
    fun getAllAsteroid() : Flow<List<CachedAsteroid>>

    @Query("DELETE FROM Asteroids WHERE closeApproachDate != :date")
    fun deleteAsteroid(date : String)

}