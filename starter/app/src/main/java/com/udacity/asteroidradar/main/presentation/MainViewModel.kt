package com.udacity.asteroidradar.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.common.domain.NetworkException
import com.udacity.asteroidradar.common.domain.NetworkUnavailableException
import com.udacity.asteroidradar.common.domain.NoImageException
import com.udacity.asteroidradar.common.presentation.model.Event
import com.udacity.asteroidradar.common.utils.createExceptionHandler
import com.udacity.asteroidradar.main.domain.usecases.GetAsteroidsUseCase
import com.udacity.asteroidradar.main.domain.usecases.GetPictureOfDayUseCase
import com.udacity.asteroidradar.main.domain.usecases.RequestAsteroidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getsAsteroidsUseCase: GetAsteroidsUseCase,
    private val getPictureOfDayUseCase: GetPictureOfDayUseCase,
    private val requestAsteroidUseCase: RequestAsteroidUseCase
) : ViewModel() {


    val state: LiveData<MainViewState> get() = _state
    private val _state = MutableLiveData<MainViewState>()

    init {
        _state.value = MainViewState()
      //  onRequestAsteroid()
        onGetLocalAsteroids()
        onRequestPictureOfDay()
    }

    private fun onRequestPictureOfDay() {
        _state.value = state.value!!.copy(loading = true)

        val errorMessage = "Failed to fetch Image of day "
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            val pic = getPictureOfDayUseCase()

            if (pic.mediaType == "image") {

                _state.value = state.value!!.copy(
                    loading = false,
                    imageOfDayUrl = pic.url
                )
            } else
                _state.value = state.value!!.copy(
                    loading = false,
                    imageOfDayUrl = pic.url
                )
                throw NoImageException("media type is video")
        }

    }


    private fun onGetLocalAsteroids() {
        _state.value = state.value!!.copy(loading = true)

        val errorMessage = "Failed to fetch Asteroid Cache is Empty "
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            getsAsteroidsUseCase().collect{

                _state.value = state.value!!.copy(
                    loading = false,
                    asteroids = it
                )
            }

        }

    }

    private fun onRequestAsteroid() {
        _state.value = state.value!!.copy(loading = true)

        val errorMessage = "Failed to fetch Asteroid from remote data source "
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {

            requestAsteroidUseCase()
        }
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException,
            is NetworkUnavailableException -> {
                _state.value = state.value!!.copy(
                    loading = false,
                    failure = Event(failure)
                )
            }
            is NoImageException -> {
                _state.value = state.value!!.copy(
                    loading = false,
                    failure = Event(failure)
                )
            }
        }
    }


}