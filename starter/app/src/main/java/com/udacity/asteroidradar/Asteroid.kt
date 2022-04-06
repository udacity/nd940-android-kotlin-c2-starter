package com.udacity.asteroidradar

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String,
                    @Json(name="close_approach_date")val closeApproachDate: String,
                    @Json(name="absolute_magnitude")val absoluteMagnitude: Double,
                    @Json(name="estimated_diameter") val estimatedDiameter: Double,
                    @Json(name="distance_from_earth")val relativeVelocity: Double, val distanceFromEarth: Double,
                    @Json(name="is_potentially_hazardous")val isPotentiallyHazardous: Boolean) : Parcelable