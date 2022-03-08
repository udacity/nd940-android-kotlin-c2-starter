package com.udacity.asteroidradar.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.MainActivity
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

                var listResult = PictureApi.retrofitService.getProperties()
                var urlString: String = ""
                _status.value = "Success: ${listResult} Picture properties retrieved"
                val responseList: List<String> = listResult.split("url")
                //var testString = "0123456789"
                Log.i("The list is",responseList.toString())

                var i = 0
                responseList.forEach {
                   // Log.i("The foreach is\n",responseList[i].toString() + '\n')
                    if (responseList[i].toString().contains("https")){
                        urlString = responseList[i]
                        urlString = urlString.substring(startIndex = 3)
                        urlString = urlString.substringBefore("jpg")
                        urlString = urlString + "jpg"
                        //Log.i("inside foreach", testString.substring(startIndex = 2)+ '\n')
                        Log.i("inside foreach", urlString+ '\n')

                    }
                    i++
                }
                _status.value = "Success: ${urlString} Picture properties retrieved"


                Log.i("url is",responseList[1])
            }catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }


        }
    }
}


