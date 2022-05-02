package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val base_url = "https://api.nasa.gov/neo/rest/v1/"
private const val api_key = "AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK"

private const val start_date = "2015-09-07"
private val end_date = "2015-09-08"

const val url = "${base_url}feed?start_date=2015-09-07&end_date=2015-09-08&api_key=AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK/"
const val img_url = "https://api.nasa.gov/planetary/apod?api_key=${api_key}"

interface AsteroidApiService {
    @GET("feed?start_date=2022-05-01&end_date=2022-05-02&api_key=AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK")
    suspend fun getAsteroids():
            String

    @GET(img_url)
    suspend fun getImageOfTheDay():
            PictureOfDay
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