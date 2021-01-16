package com.udacity.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding

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

class AsteroidViewHolder(
    val itemBinding: ItemAsteroidBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    companion object {

        fun from(parent: ViewGroup): AsteroidViewHolder {
            val binding: ItemAsteroidBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return AsteroidViewHolder(binding)
        }

        @LayoutRes
        private val LAYOUT = R.layout.item_asteroid
    }

}