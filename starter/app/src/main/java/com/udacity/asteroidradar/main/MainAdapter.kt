package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.DatabaseAsteroid

class MainAdapter(private val onClickListener: OnClickListener) :
        RecyclerView.Adapter<MainAdapter.AsteroidViewHolder>() {

    var data = listOf<DatabaseAsteroid>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_asteroid, parent, false)
        return AsteroidViewHolder(view)
    }

    class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val res = itemView.context.resources
        val codename: TextView = itemView.findViewById(R.id.codename)
        val closeApproachDate: TextView = itemView.findViewById(R.id.close_approach_date)
        val isHazerdous: ImageView = itemView.findViewById(R.id.is_hazerdous)

        fun bind(item: DatabaseAsteroid){
            codename.text = item.codename
            closeApproachDate.text = item.closeApproachDate
            isHazerdous.setImageResource(when(item.isPotentiallyHazardous){
                true -> R.drawable.ic_status_potentially_hazardous
                false -> R.drawable.ic_status_normal
            })
        }
    }
    class OnClickListener(val clickListener: (asteroid:DatabaseAsteroid) -> Unit) {
        fun onClick(asteroid:DatabaseAsteroid) = clickListener(asteroid)
    }
}