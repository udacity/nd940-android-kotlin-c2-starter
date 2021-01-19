package com.udacity.asteroidradar.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.PictureOfDay

@BindingAdapter("loadingStatus")
fun bindStatus(progressBar: ProgressBar, loadingStatus: LoadingStatus?) {
    progressBar.visibility = when (loadingStatus) {
        LoadingStatus.LOADING -> View.VISIBLE
        LoadingStatus.DONE -> View.GONE
        else -> View.GONE
    }
}

@BindingAdapter("sourceImage")
fun bindPictureOfDay(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    if (pictureOfDay == null) return

    Picasso.with(imageView.context)
        .load(pictureOfDay.url)
        .placeholder(R.drawable.placeholder_picture_of_day)
        .into(imageView)
}

@BindingAdapter("sourceAccessibility")
fun bindPictureOfDayAccessibility(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    if (pictureOfDay == null) return

    imageView.contentDescription = pictureOfDay.title
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
