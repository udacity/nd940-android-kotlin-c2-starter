package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.api.NasaPlanetaryApi.NasaApi.retrofitService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



private const val API_DIR = "neo/rest/v1/feed"

private val moshi = Moshi.Builder()
    .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NasaPlanetaryApi {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidList(
        @Query("api_key") apiKey: String
    ) : String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") apiKey: String
    ): PictureOfDay

    object NasaApi {
        val retrofitService : NasaPlanetaryApi by lazy { retrofit.create(NasaPlanetaryApi::class.java) }
    }

}
suspend fun getPictureOfTheDay() = retrofitService.getPictureOfDay(API_KEY)