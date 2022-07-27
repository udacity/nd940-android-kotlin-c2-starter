package com.udacity.asteroidradar.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding
import com.udacity.asteroidradar.domain.Asteroid

class AsteroidsViewHolder(private val binding: ItemAsteroidBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(asteroid: Asteroid, clickListener: AsteroidClickListener) {
        with(binding) {
            bAsteroid = asteroid
            bClickListener = clickListener
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup) = AsteroidsViewHolder(
            ItemAsteroidBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}