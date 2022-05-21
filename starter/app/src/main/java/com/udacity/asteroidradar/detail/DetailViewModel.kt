package com.udacity.asteroidradar.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.DatabaseAsteroid

class DetailViewModel(asteroid: DatabaseAsteroid, app: Application) : AndroidViewModel(app) {
    private val _selectedAsteroid = MutableLiveData<DatabaseAsteroid>()

    val selectedAsteroid : LiveData<DatabaseAsteroid>
            get() = _selectedAsteroid

    init {
        _selectedAsteroid.value = asteroid
    }

    val displayAsteroidHazardous = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(
            when (it.isPotentiallyHazardous) {
                true -> R.string.potentially_hazardous_asteroid_image
                false -> R.string.not_hazardous_asteroid_image
            }, it.isPotentiallyHazardous)
    }
}