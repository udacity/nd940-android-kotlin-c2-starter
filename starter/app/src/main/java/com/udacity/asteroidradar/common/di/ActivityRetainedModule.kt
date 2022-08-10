package com.udacity.asteroidradar.common.di

import com.udacity.asteroidradar.common.data.RepositoryImpl
import com.udacity.asteroidradar.common.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}