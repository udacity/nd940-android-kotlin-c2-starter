package com.udacity.asteroidradar.main.presentation

import com.udacity.asteroidradar.common.domain.model.Asteroid
import com.udacity.asteroidradar.common.presentation.model.Event

data class MainViewState (
    val loading : Boolean = true,
    val asteroids : List<Asteroid> = emptyList(),
    val imageOfDayUrl :String = "",
    val failure: Event<Throwable>? = null
)