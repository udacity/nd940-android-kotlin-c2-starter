package com.udacity.asteroidradar.ui.main.asteroidlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding

class AsteroidViewHolder(
    private val itemBinding: ItemAsteroidBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(asteroid: Asteroid, listener: OnAsteroidItemClick) {
        itemBinding.asteroid = asteroid
        itemBinding.onItemClick = listener

        itemBinding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): AsteroidViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding= ItemAsteroidBinding.inflate(inflater, parent, false)
            return AsteroidViewHolder(binding)
        }
    }
}