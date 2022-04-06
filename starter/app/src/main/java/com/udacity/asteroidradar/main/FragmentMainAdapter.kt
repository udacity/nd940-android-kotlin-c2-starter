package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class FragmentMainAdapter( val onClickListener: OnClickListener ) :
    ListAdapter<Asteroid, FragmentMainAdapter.AsteroidViewHolder>(DiffCallback) {

    class AsteroidViewHolder(private var binding: FragmentMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }


    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Asteroid]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(FragmentMainBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }
}
//class FragmentMainAdapter( val onClickListener: OnClickListener ) :
//    androidx.recyclerview.widget.ListAdapter<AsteroidList, FragmentMainAdapter.FragmentMainViewHolder>(DiffCallback) {
//    /**
//     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
//     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
//     */
//    class FragmentMainViewHolder(private var binding: FragmentMainBinding):
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(asteroidList: AsteroidList) {
//            binding.asteroid = asteroidList
//            // This is important, because it forces the data binding to execute immediately,
//            // which allows the RecyclerView to make the correct view size measurements
//            binding.executePendingBindings()
//        }
//    }
//
//    /**
//     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
//     * has been updated.
//     */
//    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
//        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//
//    /**
//     * Create new [RecyclerView] item views (invoked by the layout manager)
//     */
//    override fun onCreateViewHolder(parent: ViewGroup,
//                                    viewType: Int): MarsPropertyViewHolder {
//        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
//    }
//
//    /**
//     * Replaces the contents of a view (invoked by the layout manager)
//     */
//    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
//        val marsProperty = getItem(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
//        holder.bind(marsProperty)
//    }
//
//    /**
//     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [MarsProperty]
//     * associated with the current item to the [onClick] function.
//     * @param clickListener lambda that will be called with the current [MarsProperty]
//     */
//    class OnClickListener(val clickListener: (marsProperty:MarsProperty) -> Unit) {
//        fun onClick(marsProperty:MarsProperty) = clickListener(marsProperty)
//    }
//}