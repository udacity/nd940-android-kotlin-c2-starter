package com.udacity.asteroidradar.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaPlanetaryApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class MainViewModel : ViewModel() {
    val asteroidList = MutableLiveData(listOf<Asteroid>())

    init {
        viewModelScope.launch {
            getAsteroidList()
        }
    }
    private fun getAsteroidList() {
        viewModelScope.launch {
            try {
                val resultString = NasaPlanetaryApi.NasaApi.retrofitService.getAsteroidList(Constants.API_KEY)
                asteroidList.value = parseAsteroidsJsonResult(JSONObject(resultString))
                println(asteroidList.value)
            } catch (e: Exception) {
                println(e)
            }
        }

    }
}