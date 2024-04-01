package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.AsteroidFilter
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)

    var asteroidList: LiveData<List<Asteroid>>? = null
    // default show list of today
    private val _filter = MutableLiveData(AsteroidFilter.TODAY)


    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay


    init {
        getAsteroidList()
        getPictureOfDay()

        asteroidList = _filter.switchMap {
            when (it) {
                AsteroidFilter.TODAY -> asteroidRepository.getAsteroidsOfToday()
                AsteroidFilter.SEVEN_DAYS -> asteroidRepository.getAsteroidsInNext7Days()
                else -> asteroidRepository.getSavedAsteroids()
            }
        }
    }

    fun selectFilter(filter: AsteroidFilter) {
        _filter.value = filter
    }


    private fun getAsteroidList() {
        viewModelScope.launch {
            try {
                asteroidRepository.refreshAsteroids()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = asteroidRepository.getPictureOfDay()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}