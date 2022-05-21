package com.udacity.asteroidradar.main

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    enum class AsteroidApiStatus { LOADING, ERROR, DONE }

    // Private value that tells the status of loading the asteroids
    private val _status = MutableLiveData<AsteroidApiStatus>()

    var _asteroids = MutableLiveData<List<DatabaseAsteroid>>()

    // Private value that keeps track of the PictureOfTheDay
    val _asteroidImage = MutableLiveData<PictureOfDay>()

    // Public value that keeps track of the PictureOfTheDay
    val asteroidImage: LiveData<PictureOfDay>
        get() = _asteroidImage

    var repository: AsteroidsRepository

    // Internally, we use a MutableLiveData to handle navigation to the selected asteroid
    private val _navigateToSelectedAsteroid = MutableLiveData<DatabaseAsteroid>()

    // The external immutable LiveData for the navigation asteroid
    val navigateToSelectedAsteroid: LiveData<DatabaseAsteroid>
        get() = _navigateToSelectedAsteroid

    init {
        val asteroidDB = AsteroidsDatabase.getInstance(application).asteroidsDao
        repository = AsteroidsRepository(asteroidDB)
    }

    fun getImageOfTheDay() {
        _status.value = AsteroidApiStatus.LOADING
        viewModelScope.launch {
            try {
                val imageResult = AsteroidApi.retrofitService.getImageOfTheDay()
                // update the PictureOfDay to the newly defined imageResult
                _asteroidImage.value = imageResult
                _status.value = AsteroidApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AsteroidApiStatus.ERROR
            }
        }
    }

    fun getAsteroidsList() {
        _status.value = AsteroidApiStatus.LOADING
        viewModelScope.launch {
            try {
                repository.refreshAsteroidsList()
                _asteroids.value = repository.getAllAsteroids()
                _status.value = AsteroidApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AsteroidApiStatus.ERROR
            }
        }
    }

    fun displayAsteroidDetails(asteroid: DatabaseAsteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }
}