package com.udacity.asteroidradar.common.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun CoroutineScope.createExceptionHandler(
    message: String,
    crossinline action: (throwable: Throwable) -> Unit
) = CoroutineExceptionHandler { _, throwable ->
    Logger.e(throwable, message)
    throwable.printStackTrace()

    launch {
        action(throwable)
    }
}


fun ImageView.setImage(url: String) {

    if (url.isNotEmpty()) {

        Picasso.with(this.context)
            .load(url)
            .error(R.drawable.placeholder_picture_of_day)
            .into(this)

    }
}