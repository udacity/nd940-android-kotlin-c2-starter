package com.udacity.asteroidradar.common.utils

import com.udacity.asteroidradar.common.Constants
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getCurrentDate(): String {
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return df.format(c)
    }


    fun getNextSevenDaysFormattedDates(): ArrayList<String> {
        val formattedDateList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            formattedDateList.add(dateFormat.format(currentTime))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return formattedDateList
    }
}

