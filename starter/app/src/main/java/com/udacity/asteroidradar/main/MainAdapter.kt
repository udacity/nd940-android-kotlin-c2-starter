package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.main.MainViewModel.AsteroidViewHolder

class MainAdapter : RecyclerView.Adapter<AsteroidViewHolder>() {
    var data = listOf<DatabaseAsteroid>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text  = item.closeApproachDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_asteroid, parent, false) as TextView
        return AsteroidViewHolder(view)
    }

}