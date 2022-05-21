package com.udacity.asteroidradar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.database.DatabaseAsteroid

class DetailViewModel(asteroid: DatabaseAsteroid, app: Application) : AndroidViewModel(app) {
    private val _selectedAsteroid = MutableLiveData<DatabaseAsteroid>()

    val selectedAsteroid : LiveData<DatabaseAsteroid>
            get() = _selectedAsteroid

    init {
        _selectedAsteroid.value = asteroid
    }
}