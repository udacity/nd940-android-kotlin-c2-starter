package com.udacity.asteroidradar.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.Asteroid

@Entity(
    tableName = "asteroid_table"
)
data class AsteroidTable(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String, // TODO : change this to be a date from SQL Date
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) {

    fun asDomainModel() = Asteroid(
        id,
        codename,
        closeApproachDate,
        absoluteMagnitude,
        estimatedDiameter,
        relativeVelocity,
        distanceFromEarth,
        isPotentiallyHazardous
    )

}

