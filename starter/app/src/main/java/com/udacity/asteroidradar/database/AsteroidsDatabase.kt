package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class AsteroidsDatabase : RoomDatabase() {

    abstract val asteroidsDao: AsteroidsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AsteroidsDatabase? = null

        fun getInstance(context: Context): AsteroidsDatabase {
            /* Multiple threads can ask for the database at the same time, Only one thread may enter a synchronized block at a time. */
            synchronized(this) {
                /* Copy the current value of INSTANCE to a local variable so Kotlin can smart cast. Smart cast is only available to local variables. */
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidsDatabase::class.java,
                        "asteroids_database"
                    ).fallbackToDestructiveMigration()
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        .build()
                        // Assign INSTANCE to the newly created database.
                        INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }

}