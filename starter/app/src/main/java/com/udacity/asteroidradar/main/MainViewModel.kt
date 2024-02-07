package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.PictureOfDay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = MainRepository()

    init {
        refreshPictureOfDay()
    }

    val pictureOfDay : LiveData<PictureOfDay> = repo.pictureOfDay

    fun refreshPictureOfDay() {
        viewModelScope.launch {
            try {
                repo.updateImageOfDay()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}