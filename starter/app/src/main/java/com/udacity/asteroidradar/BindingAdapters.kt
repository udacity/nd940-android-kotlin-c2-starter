package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

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

@BindingAdapter("pictureOfDay")
fun ImageView.bindPictureOfDay(pictureOfDay: PictureOfDay?) {
    if (pictureOfDay != null && pictureOfDay.url.isNotBlank()) {
        Picasso.with(context)
            .load(pictureOfDay.url)
            .placeholder(R.drawable.placeholder_picture_of_day)
//            .error(R.drawable.no_image_available)
            .fit()
            .centerCrop()
            .into(this)

        contentDescription = String.format(
            context.getString(R.string.nasa_picture_of_day_content_description_format),
            pictureOfDay.title
        )
    } else {
//        setImageResource(R.drawable.no_image_available)
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        contentDescription =
            context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
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
