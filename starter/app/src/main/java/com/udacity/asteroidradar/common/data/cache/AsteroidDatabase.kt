package com.udacity.asteroidradar.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.common.data.cache.daos.AsteroidDao
import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid


@Database(
    entities = [CachedAsteroid::class],
    version = 1
)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract fun asteroidDao() : AsteroidDao

}