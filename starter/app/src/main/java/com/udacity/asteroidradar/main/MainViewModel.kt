package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.network.AsteroidApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {
    // Private value that tells the status of loading the asteroids
    private val _status = MutableLiveData<AsteroidApiStatus>()

    // Public value that tells the status of loading the asteroids
    val status: LiveData<AsteroidApiStatus>
        get() = _status

    // Private value to keep track of the Asteroids List
    private val _asteroids = MutableLiveData<List<Asteroid>>()

    // Public variable to keep track of the Asteroids List
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    // Private value that keeps track of the PictureOfTheDay
    val _asteroidImage = MutableLiveData<PictureOfDay>()

    // Public value that keeps track of the PicureOfTheDay
    val asteroidImage: LiveData<PictureOfDay>
        get() = _asteroidImage

    // Private value that navigates to the selected asteroid
    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()

    // Public value that navigates to the selected asteroid
    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid


    fun getAsteroidsList() {
        // use the viewModelScope to execute the following with CoroutineScope to run in the back-process
        viewModelScope.launch {
            try{
                val asteroidsResult = AsteroidApi.retrofitService.getAsteroids()
                if (asteroidsResult.isNotEmpty()) {
                    // Set the list of asteroids to the newly defined asteroidsResult
                    _asteroids.value = asteroidsResult
                    _status.value = AsteroidApiStatus.DONE
                }
            } catch (e: Exception) {
                _status.value = AsteroidApiStatus.ERROR
                _asteroids.value = ArrayList()
            }
        }
    }

    fun getImageOfTheDay() {
        viewModelScope.launch {
            try{
                val imageResult = AsteroidApi.retrofitService.getImageOfTheDay()
                // update the PictureOfDay to the newly defined imageResult
                _asteroidImage.value = imageResult
            } catch (e: Exception) {
            _status.value = AsteroidApiStatus.ERROR
            }
        }
    }

    fun displayAsteroidDetail(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }
}