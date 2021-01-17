package com.udacity.asteroidradar.ui.main.asteroidlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.data.Asteroid

class OnAsteroidItemClick(val block: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = block(asteroid)
}

class AsteroidAdapter(private val onAsteroidItemClick: OnAsteroidItemClick) :
    RecyclerView.Adapter<AsteroidViewHolder>() {

    var asteroids: List<Asteroid> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.itemBinding.also {
            it.asteroid = asteroids[position]
            it.onItemClick = onAsteroidItemClick
        }
    }

    override fun getItemCount(): Int = asteroids.size

}
