package com.udacity.asteroidradar.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.data.Asteroid

@Entity(tableName = "asteroid_table")
data class DatabaseAsteroid constructor(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0L,

    @ColumnInfo(name = "code_name")
    var codeName: String = "",

    @ColumnInfo(name = "close_approach_date")
    var closeApproachDate: String,

    @ColumnInfo(name = "absolute_magnitude")
    var absoluteMagnitude: Double = 0.0,

    @ColumnInfo(name = "estimated_diameter")
    var estimatedDiameter: Double = 0.0,

    @ColumnInfo(name = "relative_velocity")
    var relativeVelocity: Double = 0.0,

    @ColumnInfo(name = "distance_from_earth")
    var distanceFromEarth: Double = 0.0,

    @ColumnInfo(name = "is_potentially_hazardous")
    var isPotentiallyHazardous: Boolean = false
)

fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> = map {
    Asteroid(
        id = it.id,
        codename = it.codeName,
        closeApproachDate = it.closeApproachDate,
        absoluteMagnitude = it.absoluteMagnitude,
        estimatedDiameter = it.estimatedDiameter,
        relativeVelocity = it.relativeVelocity,
        distanceFromEarth = it.distanceFromEarth,
        isPotentiallyHazardous = it.isPotentiallyHazardous
    )
}