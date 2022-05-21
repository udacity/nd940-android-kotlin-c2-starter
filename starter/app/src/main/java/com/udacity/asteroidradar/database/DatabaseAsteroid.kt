package com.udacity.asteroidradar.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.Asteroid

@Entity(tableName = "asteroids_database")
data class DatabaseAsteroid(
    @ColumnInfo(name = "id")
    val id: Long,
    @PrimaryKey
    @ColumnInfo(name = "name")
    val codename: String,
    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,
    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimated_diameter")
    val estimatedDiameter: Double,
    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double,
    @ColumnInfo(name = "distance_from_earth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "is_potentially_hazardous")
    val isPotentiallyHazardous: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(codename)
        parcel.writeString(closeApproachDate)
        parcel.writeDouble(absoluteMagnitude)
        parcel.writeDouble(estimatedDiameter)
        parcel.writeDouble(relativeVelocity)
        parcel.writeDouble(distanceFromEarth)
        parcel.writeByte(if (isPotentiallyHazardous) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DatabaseAsteroid> {
        override fun createFromParcel(parcel: Parcel): DatabaseAsteroid {
            return DatabaseAsteroid(parcel)
        }

        override fun newArray(size: Int): Array<DatabaseAsteroid?> {
            return arrayOfNulls(size)
        }
    }
}

fun ArrayList<Asteroid>.asDatabaseModel() : List<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid (
            id = it.id,
            codename =it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}
