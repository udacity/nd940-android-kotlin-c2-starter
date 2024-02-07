package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NeoWsService

class MainRepository {

    private val _neoWsService = NeoWsService()
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()

    val pictureOfDay : LiveData<PictureOfDay> = _pictureOfDay

    suspend fun updateImageOfDay() {
        _pictureOfDay.value = _neoWsService.fetchPictureOfDay()
    }
}