package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.getSeventhDay
import com.udacity.asteroidradar.api.getToday
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDatabase.getDatabase(application)
    private val asteroidRepository = AsteroidRepositoryImpl(database)

    private val _asteroids: MutableLiveData<List<Asteroid>> = MutableLiveData()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _pictureOfDay: MutableLiveData<PictureOfDay> = MutableLiveData()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay


    init {
        onViewWeekAsteroidsClicked()
        viewModelScope.launch {
            try {
                asteroidRepository.refreshAsteroids()
                getPictureOfDay()
            } catch (e: Exception) {
                println("Exception refreshing data: $e.message")
            }
        }
    }

    private suspend fun getPictureOfDay() {
        _pictureOfDay.value = asteroidRepository.getPictureOfDay()
    }

    fun onViewWeekAsteroidsClicked() {
        viewModelScope.launch {
            database.asteroidDao.getAsteroidsByCloseApproachDate(
                getToday(),
                getSeventhDay()
            ).collect { asteroids ->
                _asteroids.value = asteroids
            }

        }
    }

    fun onTodayAsteroidsClicked() {
        viewModelScope.launch {
            database.asteroidDao.getAsteroidsByCloseApproachDate(
                getToday(),
                getToday()
            ).collect { asteroids ->
                _asteroids.value = asteroids
            }
        }
    }

    fun onSavedAsteroidsClicked() {
        viewModelScope.launch {
            database.asteroidDao.getAsteroids().collect { asteroids ->
                _asteroids.value = asteroids
            }
        }
    }
}