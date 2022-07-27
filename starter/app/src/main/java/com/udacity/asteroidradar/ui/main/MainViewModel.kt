package com.udacity.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.model.ImageOfDayResponse
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(private val app:Application) : AndroidViewModel(app) {

    private val database by lazy { AsteroidDatabase.getDatabase(app.applicationContext) }
    private val repo by lazy { AsteroidRepository(database) }


    // cashed
    private val _imageOfTheDay = MutableLiveData<ImageOfDayResponse>()

    // public
    val imageOfTheDay: LiveData<ImageOfDayResponse> get() = _imageOfTheDay
    val asteroids: LiveData<List<Asteroid>> get() = repo.asteroids

    init {
        getImageOfTheDay()
        repo.refreshAsteroids()
    }

    private fun getImageOfTheDay() = viewModelScope.launch {
        _imageOfTheDay.value = repo.getImageOfTheDay()
    }

}