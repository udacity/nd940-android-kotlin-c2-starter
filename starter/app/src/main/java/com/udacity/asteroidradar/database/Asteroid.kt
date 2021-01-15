package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_table")
data class Asteroid(
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