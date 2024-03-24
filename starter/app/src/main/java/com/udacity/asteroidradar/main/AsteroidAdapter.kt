package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter: RecyclerView.Adapter<AsteroidItemViewHolder>() {
    var data = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AsteroidItemViewHolder, position: Int) {
        val item = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate()
    }

    class AsteroidItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): AsteroidItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.asteroid_list_item, parent, false)
                return AsteroidItemViewHolder(view)
            }
        }
    }
}