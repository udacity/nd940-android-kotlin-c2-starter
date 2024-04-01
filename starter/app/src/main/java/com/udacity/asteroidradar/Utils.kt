package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
fun Date.toDatabaseString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    return formatter.format(this)
}