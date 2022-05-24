package com.udacity.asteroidradar.detail

import android.app.Application
import android.content.Context
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

    val displayAsteroidIsPotentiallyHazardous = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(
            when (it.isPotentiallyHazardous) {
                true -> R.string.potentially_hazardous_asteroid_image
                false -> R.string.not_hazardous_asteroid_image
            }, it.isPotentiallyHazardous)
    }

    val displayAsteroidCodename = Transformations.map(selectedAsteroid){
        app.applicationContext.getString(it.codename)
    }

    val displayAsteroidCloseApproachDate = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(it.closeApproachDate)
    }

    val displayAsteroidAbsoluteMagnitude = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(it.absoluteMagnitude)
    }

    val displayAsteroidEstimatedDiameter = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(it.estimatedDiameter)
    }

    val displayAsteroidRelativeVelocity = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(it.relativeVelocity)
    }

    val displayAsteroidDistanceFromEarth = Transformations.map(selectedAsteroid) {
        app.applicationContext.getString(it.distanceFromEarth)
    }
}

private fun Context.getString(double: Double): String {
return double.toString()
}

private fun Context.getString(string: String): String {
    return string
}
