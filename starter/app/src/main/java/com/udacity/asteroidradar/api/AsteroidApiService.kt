package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val base_url = Constants.BASE_URL
private const val api_key = "AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK"
const val img_url = "https://api.nasa.gov/planetary/apod?api_key=${api_key}"

interface AsteroidApiService {

    @GET(img_url)
    suspend fun getImageOfTheDay():
            PictureOfDay

    @GET("feed?")
    suspend fun getAsteroids(
        // Queries annotations to pass the dynamic date & API key to retrofit
        @Query(Constants.START_DATE) todayDate:String = getDate(),
        @Query(Constants.API_KEY) apiKey:String = api_key
    ): String


}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(base_url)
    .build()



object AsteroidApi {
    val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}