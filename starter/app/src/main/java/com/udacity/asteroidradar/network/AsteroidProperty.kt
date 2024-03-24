package com.udacity.asteroidradar.network

import com.squareup.moshi.Json

data class AsteroidProperty(
    @Json(name = "id") val id: String,
    @Json(name = "absolute_magnitude") val absoluteMagnitude: Float,
    @Json(name = "estimated_diameter_max") val estimatedDiameterMax: Float,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean,
    @Json(name = "kilometers_per_second") val kilometerPerSecond: Float,
    @Json(name = "astronomical") val astronomical: Float,
)
