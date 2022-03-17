package com.udacity.asteroidradar.api

import com.squareup.moshi.Json

data class AsteroidList (
    val id: String,
    @Json(name = "estimated_diameter_max") val estimatedDiameterMax: Double,
    @Json(name = "is_potentially_hazardous_asteroid") val is_PotentiallyHazardous: Boolean,
    @Json(name = "kilometers_per_second") val kilometersPerSecond: String,
    val astronomical: String
    )