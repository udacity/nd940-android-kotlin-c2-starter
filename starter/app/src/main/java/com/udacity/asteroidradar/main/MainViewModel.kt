package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.network.AsteroidApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {



    private val _status = MutableLiveData<AsteroidApiStatus>()

    val status: LiveData<AsteroidApiStatus>
        get() = _status

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _asteroids = MutableLiveData<List<Asteroid>>()

    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()

    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid



    init {
        getAsteroids()
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            try{
                var listResult = AsteroidApi.retrofitService.getAsteroids()
                _response.value = "Success ${listResult.size} Asteroid Properties received"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
//        viewModelScope.launch {
//            _status.value = AsteroidApiStatus.LOADING
//            try {
////                _asteroids.value = AsteroidApi.retrofitService.getAsteroids()
//                _status.value = AsteroidApiStatus.DONE
//            } catch (e: Exception) {
//                _status.value = AsteroidApiStatus.ERROR
//                _asteroids.value = ArrayList()
//            }
//        }
        }

    fun displayAsteroidDetail(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }
}