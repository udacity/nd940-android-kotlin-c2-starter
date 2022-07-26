package com.udacity.asteroidradar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.db.entities.AsteroidTable

@Database(
    entities = [AsteroidTable::class],
    version = 1
)
abstract class AsteroidDatabase: RoomDatabase() {
    abstract val asteroidDao: AsteroidDao

    companion object {
        private lateinit var INSTANCE: AsteroidDatabase

        fun getDatabase(context: Context): AsteroidDatabase {
            synchronized(AsteroidDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AsteroidDatabase::class.java,
                        "asteroid_database.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}

