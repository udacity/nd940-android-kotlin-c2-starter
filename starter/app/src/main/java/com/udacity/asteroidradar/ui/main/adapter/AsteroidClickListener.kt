package com.udacity.asteroidradar.ui.main.adapter

import com.udacity.asteroidradar.domain.Asteroid

class AsteroidClickListener(val clickListener: (Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}
