package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.Asteroid
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.nasa.gov/"
private const val API_DIR = "neo/rest/v1/feed"
private const val API_KEY = "Ly5IjJrVSY1TfymLtTdOQhQ7D6sNB6Qpv13NYXGU";

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
    fun getAsteroidList(
        @Query("api_key") apiKey: String
    ) : Call<String>

    @GET("planetary/apod")
    fun getPictureOfDay(
        @Query("api_key") apiKey: String
    ): Call<String>

    object NasaApi {
        val retrofitService : NasaPlanetaryApi by lazy { retrofit.create(NasaPlanetaryApi::class.java) }
    }

}