package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.data.PictureOfDay
import kotlinx.coroutines.runBlocking

class PictureOfDayRepository {

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = runBlocking {
            _pictureOfDay.value = NasaApi.retrofitService.getPictureOfDay()
            _pictureOfDay
        }
}