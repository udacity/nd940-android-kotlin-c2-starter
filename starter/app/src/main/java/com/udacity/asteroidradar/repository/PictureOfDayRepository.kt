package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.data.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureOfDayRepository {

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    @Throws(Throwable::class)
    suspend fun refreshPictureOfDay() {
        withContext(Dispatchers.IO) {
            NasaApi.retrofitService.getPictureOfDay()?.let {
                _pictureOfDay.postValue(it)
            }
        }
    }
}