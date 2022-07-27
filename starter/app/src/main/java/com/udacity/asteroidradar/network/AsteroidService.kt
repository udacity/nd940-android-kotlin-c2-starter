package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.network.model.ImageOfDayResponse
import com.udacity.asteroidradar.utile.Constants.API_KEY
import com.udacity.asteroidradar.utile.Constants.END_DATE
import com.udacity.asteroidradar.utile.Constants.START_DATE
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {

    /**
     * # NeoFeed
     *
     * Parameter	Type	 Default	 Description
     *
     * start_date	YYYY-MM-DD	none	 Starting date for asteroid search
     *
     * end_date YYYY-MM-DD 7 days after start_date Ending date for asteroid
     * search
     *
     * api_key	 string	 DEMO_KEY	 api.nasa.gov key for expanded usage
     */
    @GET("neo/rest/v1/feed")
    fun getNeoFeedAsync(
        @Query(START_DATE) startDate: String, // Date Format -> YYYY-MM-DD
        @Query(END_DATE) endDate: String, // Date Format -> YYYY-MM-DD
        @Query(API_KEY) apiKey: String
    ): Deferred<String>


    /** # NASA image of the day */
    @GET("planetary/apod")
    suspend fun nasaImageOfTheDay(
        @Query(API_KEY) apiKey: String
    ): ImageOfDayResponse

}