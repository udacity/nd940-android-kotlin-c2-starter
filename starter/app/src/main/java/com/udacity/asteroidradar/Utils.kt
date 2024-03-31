package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// https://stackoverflow.com/questions/68191152/convert-string-date-to-date-in-android-java-kotlin-without-having-to-deal-with
fun dateToString(date: Long): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}