package com.udacity.asteroidradar.common.data.di

import android.content.Context
import androidx.room.Room
import com.udacity.asteroidradar.common.data.cache.AsteroidCache
import com.udacity.asteroidradar.common.data.cache.AsteroidDatabase
import com.udacity.asteroidradar.common.data.cache.Cache
import com.udacity.asteroidradar.common.data.cache.daos.AsteroidDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache : AsteroidCache) : Cache

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ) : AsteroidDatabase {

            return Room.databaseBuilder(
                context,
                AsteroidDatabase::class.java,
                "asteroid.db"
            ).build()
        }

        @Provides
        fun provideAsteroidDao(
            asteroidDatabase: AsteroidDatabase
        ) : AsteroidDao = asteroidDatabase.asteroidDao()
    }
}