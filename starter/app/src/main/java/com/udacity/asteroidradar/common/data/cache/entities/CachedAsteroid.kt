package com.udacity.asteroidradar.common.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.common.domain.model.Asteroid


@Entity(tableName = "Asteroids")
data class CachedAsteroid(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) {
    companion object {

        fun fromDomain(domainModel: Asteroid): CachedAsteroid {


            return CachedAsteroid(
                id = domainModel.id,
                codename = domainModel.codename,
                closeApproachDate = domainModel.closeApproachDate,
                absoluteMagnitude = domainModel.absoluteMagnitude,
                estimatedDiameter = domainModel.estimatedDiameter,
                relativeVelocity = domainModel.relativeVelocity,
                distanceFromEarth = domainModel.distanceFromEarth,
                isPotentiallyHazardous = domainModel.isPotentiallyHazardous
            )
        }
    }


    fun toDomain(): Asteroid {

        return Asteroid(
            id = id,
            codename = codename,
            closeApproachDate = closeApproachDate,
            absoluteMagnitude = absoluteMagnitude,
            estimatedDiameter = estimatedDiameter,
            relativeVelocity = relativeVelocity,
            distanceFromEarth = distanceFromEarth,
            isPotentiallyHazardous = isPotentiallyHazardous
        )
    }
}