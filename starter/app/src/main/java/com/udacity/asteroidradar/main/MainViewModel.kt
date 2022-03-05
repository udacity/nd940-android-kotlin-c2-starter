package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.PictureApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<PictureOfDay>()

    val property: LiveData<PictureOfDay>
        get() = _property

    /**
     * Call getPictureProperties() on init so we can display status immediately.
     */
    init {
        getPictureProperties()
    }

    /**
     * Sets the value of the status LiveData to the Picture API status.
     */
    private fun getPictureProperties() {
        viewModelScope.launch {
            try{
                var listResult =
                    PictureApi.retrofitService.getProperties()
                _status.value = "Success: ${listResult} Picture properties retrieved"
            }catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }
    }
}


