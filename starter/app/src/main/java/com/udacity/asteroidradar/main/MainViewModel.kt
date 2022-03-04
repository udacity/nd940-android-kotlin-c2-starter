package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.api.PictureApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

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
                var listResult = PictureApi.retrofitService.getProperties().toString()
                _response.value = "Success: ${listResult} Picture properties retrieved"
            }catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }

        }
    }
}


