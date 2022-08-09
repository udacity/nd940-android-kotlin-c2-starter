package com.udacity.asteroidradar.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.domain.model.PictureOfDay

@JsonClass(generateAdapter = true)
data class ApiPictureOfDay(
    @Json(name = "media_type") val mediaType: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "url") val url: String? = null
) {
    companion object {

        fun mapToDomain(input: ApiPictureOfDay): PictureOfDay = PictureOfDay(
            mediaType = input.mediaType.orEmpty(),
            title = input.title.orEmpty(),
            url = input.url.orEmpty()
        )
    }
}