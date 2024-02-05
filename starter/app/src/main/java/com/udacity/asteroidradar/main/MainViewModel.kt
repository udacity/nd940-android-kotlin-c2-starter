package com.udacity.asteroidradar.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repo = MainRepository()

    init {
        getLatest()
    }

    fun getLatest() {
        viewModelScope.launch {
            try {
                repo.fetchLatest()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}