package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_database")
data class Asteroid(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name="codename") val codename: String?,
    @ColumnInfo(name = "close_approach_date") val closeApproachDate: String?,
    @ColumnInfo(name = "absolute_magnitude") val absoluteMagnitude: Double?,
    @ColumnInfo(name = "estimated_diameter") val estimatedDiameter: Double?,
    @ColumnInfo(name = "relative_velocity") val relativeVelocity: Double?,
    @ColumnInfo(name = "distance_from_earth") val distanceFromEarth: Double,
    @ColumnInfo(name = "is_potentially_hazardous") val isPotentiallyHazardous: Boolean?
)

fun List<Asteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}