package com.udacity.asteroidradar.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.common.domain.model.Asteroid
import com.udacity.asteroidradar.databinding.RecyclerViewAsteroidItemBinding

class AsteroidAdapter : ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(ITEM_COMPARATOR) {

    var asteroidClickListener: AsteroidClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {

        val binding = RecyclerViewAsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item: Asteroid = getItem(position)
        holder.bind(item)
    }


    inner class AsteroidViewHolder(
        private val binding: RecyclerViewAsteroidItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Asteroid) {
            binding.model = item

            binding.root.setOnClickListener {
                asteroidClickListener?.onClick(item)
            }
        }
    }
}
interface AsteroidClickListener {
    fun onClick(asteroid: Asteroid)
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}