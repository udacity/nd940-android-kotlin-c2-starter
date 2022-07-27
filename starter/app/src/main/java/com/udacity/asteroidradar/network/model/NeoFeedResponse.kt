package com.udacity.asteroidradar.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.db.entities.AsteroidTable
import com.udacity.asteroidradar.domain.Asteroid

@JsonClass(generateAdapter = true)
data class NeoFeedResponse(
    @Json(name = "near_earth_objects")
    val nearEarthObjects: NearEarthObjects,
    @Json(name = "element_count")
    val elementCount: Int,
    val links: Links
)

@JsonClass(generateAdapter = true)
data class Links(
    val next: String,
    val prev: String,
    val self: String
)

@JsonClass(generateAdapter = true)
data class NearEarthObjects(
    @Json(name = "near_earth_objects")
    val nearEarthObject: List<NearEarthObject>
)

@JsonClass(generateAdapter = true)
data class NearEarthObject(
    val id: Long,
    val name: String,
    @Json(name = "absolute_magnitude_h")
    val absoluteMagnitude: Double,
    @Json(name = "estimated_diameter")
    val estimatedDiameter: EstimatedDiameter,
    @Json(name = "is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    @Json(name = "close_approach_data")
    val closeApproachData: List<CloseApproachData>,
)

@JsonClass(generateAdapter = true)
data class EstimatedDiameter(
    val kilometers: Kilometers,
)

@JsonClass(generateAdapter = true)
data class Kilometers(
    val estimated_diameter_max: Double,
    val estimated_diameter_min: Double
)

@JsonClass(generateAdapter = true)
data class CloseApproachData(
    @Json(name = "close_approach_date")
    val closeApproachDate: String,
    @Json(name = "miss_distance")
    val missDistance: MissDistance,
    @Json(name = "relative_velocity")
    val relativeVelocity: RelativeVelocity,
)

@JsonClass(generateAdapter = true)
data class MissDistance(
    val astronomical: String,
)

@JsonClass(generateAdapter = true)
data class RelativeVelocity(
    val kilometers_per_second: String,
)


