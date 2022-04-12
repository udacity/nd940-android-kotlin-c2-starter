package com.udacity.asteroidradar.main

import android.util.Log
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

    private val _status = MutableLiveData<AsteroidApiStatus>()


    val status: LiveData<AsteroidApiStatus>
        get() = _status

    private val _asteroids = MutableLiveData<List<Asteroid>>()

    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    // Get the ImageOfTheDay
    val _asteroidImage = MutableLiveData<PictureOfDay>()

    // get the PictureOfDay Model
//    val asteroidImage: LiveData<PictureOfDay>
//        get() = _asteroidImage

    // navigate to the selected asteroid
    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()

    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid


    fun getAsteroidsList() {
        viewModelScope.launch {
            try{
                val listResult = AsteroidApi.retrofitService.getAsteroids()

                if (listResult.isNotEmpty()) {
                    _asteroids.value = listResult
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