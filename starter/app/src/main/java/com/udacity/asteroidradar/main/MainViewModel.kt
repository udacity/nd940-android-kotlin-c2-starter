package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.api.AsteroidApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<AsteroidApiStatus>()

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response


    init{

    }

    private fun getAsteroids() {
    AsteroidApi.retrofitService.getAsteroids().enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            _response.value = "Failure: " + t.message
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            _response.value = response.body()
        }
    })
}
}