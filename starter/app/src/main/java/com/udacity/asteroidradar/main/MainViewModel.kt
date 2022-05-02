package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    // Private value that tells the status of loading the asteroids
    private val _status = MutableLiveData<AsteroidApiStatus>()

    val _asteroids = MutableLiveData<ArrayList<Asteroid>>()

    // Private value that keeps track of the PictureOfTheDay
    val _asteroidImage = MutableLiveData<PictureOfDay>()

    // Public value that keeps track of the PicureOfTheDay
    val asteroidImage: LiveData<PictureOfDay>
        get() = _asteroidImage
    fun getImageOfTheDay() {
        _status.value = AsteroidApiStatus.LOADING
        viewModelScope.launch {
            try {
                val imageResult = AsteroidApi.retrofitService.getImageOfTheDay()
                // update the PictureOfDay to the newly defined imageResult
                _asteroidImage.value = imageResult
                _status.value = AsteroidApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AsteroidApiStatus.ERROR
            }
        }
    }

    fun getAsteroidsList() {
        _status.value = AsteroidApiStatus.LOADING
        viewModelScope.launch {
            try {
                val asteroidsList = AsteroidApi.retrofitService.getAsteroids()
                val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroidsList))
                // update the PictureOfDay to the newly defined imageResult
                _asteroids.value = parsedAsteroids
                _status.value = AsteroidApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AsteroidApiStatus.ERROR
            }
        }
    }

}