package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants

@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao : AsteroidDao

    companion object{
        @Volatile
        private lateinit var INSTANCE : AsteroidDatabase

        fun getDatabase(context: Context):AsteroidDatabase{
            synchronized(this){
                if(!::INSTANCE.isInitialized){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        "Asteroid_DB")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}