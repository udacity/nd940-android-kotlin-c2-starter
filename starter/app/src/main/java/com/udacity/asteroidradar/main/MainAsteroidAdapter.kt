package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidInfoBinding

class MainAsteroidAdapter(
    private val listener: AsteroidListener
) : ListAdapter<Asteroid, RecyclerView.ViewHolder>(AsteroidDiffCallback()) {

    class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder as AsteroidViewHolder
        holder.bind(asteroid, listener)
    }

    class AsteroidViewHolder(private val binding: ItemAsteroidInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid, listener: AsteroidListener) {
            binding.asteriod = asteroid
            binding.executePendingBindings()
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAsteroidInfoBinding.inflate(layoutInflater, parent, false)
                return AsteroidViewHolder(binding)
            }
        }
    }

    class AsteroidListener(val listener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = listener(asteroid)
    }
}