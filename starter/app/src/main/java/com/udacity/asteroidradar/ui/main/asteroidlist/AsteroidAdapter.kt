package com.udacity.asteroidradar.ui.main.asteroidlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.udacity.asteroidradar.data.Asteroid

class AsteroidAdapter(
    private val onAsteroidItemClick: OnAsteroidItemClick
) : ListAdapter<Asteroid, AsteroidViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(getItem(position), onAsteroidItemClick)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {

        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class OnAsteroidItemClick(val block: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = block(asteroid)
}
