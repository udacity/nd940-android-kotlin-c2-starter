package com.udacity.asteroidradar.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.udacity.asteroidradar.domain.Asteroid

object AsteroidComparator : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem == newItem
}