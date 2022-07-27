package com.udacity.asteroidradar.utile

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R

@BindingAdapter("imageOfTheDay")
fun ImageView.setImageOfTheDay(url: String?) {
    Picasso.get().load(url).fit().centerCrop()
        .placeholder(R.drawable.placeholder_picture_of_day)
        .error(R.drawable.ic_baseline_error_24)
        .into(this)
}
