package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.model.ImageOfDayResponse
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: AsteroidRepository
) : ViewModel() {

    // cashed
    private val _imageOfTheDay = MutableLiveData<ImageOfDayResponse>()

    // public
    val imageOfTheDay: LiveData<ImageOfDayResponse> get() = _imageOfTheDay
    val asteroids: LiveData<List<Asteroid>> get() = repo.asteroids

    init {
        getImageOfTheDay()
    }

    private fun getImageOfTheDay() = viewModelScope.launch {
        _imageOfTheDay.value = repo.getImageOfTheDay()
    }

}