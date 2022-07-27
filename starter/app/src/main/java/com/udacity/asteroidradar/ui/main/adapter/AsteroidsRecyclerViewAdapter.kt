package com.udacity.asteroidradar.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.ui.main.adapter.AsteroidsViewHolder.Companion.from

class AsteroidsRecyclerViewAdapter(
    val asteroidClickListener: AsteroidClickListener
) :
    ListAdapter<Asteroid, AsteroidsViewHolder>(AsteroidComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = from(parent)

    override fun onBindViewHolder(holder: AsteroidsViewHolder, position: Int) =
        holder.bind(getItem(position), asteroidClickListener)

}


