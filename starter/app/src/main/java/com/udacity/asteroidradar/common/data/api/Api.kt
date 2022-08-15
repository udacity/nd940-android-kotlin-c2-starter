package com.udacity.asteroidradar.common.data.api

import com.udacity.asteroidradar.common.Constants
import com.udacity.asteroidradar.common.data.api.model.ApiPictureOfDay
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(ApiConstants.NEO_FEED_ENDPOINT)
    suspend fun fetchAsteroid(
        @Query(ApiParameters.API_KEY) apiKey :String = Constants.APIKEY,
        @Query(ApiParameters.START_DATE) startDate : String,
        @Query(ApiParameters.END_DATE) endDate : String
    ) : String

    @GET(ApiConstants.PLANETARY_ENDPOINT)
    suspend fun fetchPictureOfDay(
        @Query(ApiParameters.API_KEY) apiKey :String = Constants.APIKEY,
    ) : ApiPictureOfDay

}