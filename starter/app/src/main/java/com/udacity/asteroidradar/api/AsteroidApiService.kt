package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.nasa.gov/neo/rest/v1/"
private val api_key = "AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK"

private val start_date = "2015-09-07"
private val end_date = "2015-09-08"

val url = "${BASE_URL}feed?start_date=${start_date}&end_date=${end_date}&api_key=${api_key}/"
private val img_url = "https://api.nasa.gov/planetary/apod?api_key=${api_key}"
//feed?start_date=2015-09-07&end_date=2015-09-08&api_key=AuLTKnSJnJ6atbnq6Yz4bIh4mnDbclmtCJiamhvK/

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AsteroidApiService {
    @GET("asteroids")
    fun getAsteroids():
            List<Asteroid>
}

object AsteroidApi {
    val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}