package com.udacity.asteroidradar.util.extension

import org.json.JSONObject

fun String.toJSONObject(): JSONObject? = if (this.isEmpty()) null else JSONObject(this)