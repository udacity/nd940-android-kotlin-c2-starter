package com.udacity.asteroidradar.ui.main.asteroidlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding

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