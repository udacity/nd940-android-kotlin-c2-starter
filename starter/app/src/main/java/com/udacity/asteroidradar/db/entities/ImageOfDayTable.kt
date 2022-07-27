package com.udacity.asteroidradar.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_day_table")
data class ImageOfDayTable(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val url: String,
    @ColumnInfo(name = "media_type") val mediaType: String,
    val title: String,
)

