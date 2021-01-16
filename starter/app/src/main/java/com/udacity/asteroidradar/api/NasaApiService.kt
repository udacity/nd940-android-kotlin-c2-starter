package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface NasaApiService {

    @GET("planetary/apod")
    fun getPictureOfDay(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : PictureOfDay

    @GET("neo/rest/v1/feed")
    fun getAsteroids(
        @Query("start_date")
        startDate: String = getNextSevenDaysFormattedDates().first(),
        @Query("end_date")
        endDate: String = getNextSevenDaysFormattedDates().last(),
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Deferred<List<Asteroid>>
}

object NasaApi {
    val retrofitService: NasaApiService by lazy { retrofit.create(NasaApiService::class.java) }
}

