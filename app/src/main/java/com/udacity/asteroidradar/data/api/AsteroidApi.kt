package com.udacity.asteroidradar.data.api

import com.udacity.asteroidradar.data.api.util.RetrofitInit
import com.udacity.asteroidradar.domain.model.PictureOfDay
import com.udacity.asteroidradar.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApiService {
    @GET(Constants.ASTEROIDS_URL)
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Any

    @GET(Constants.IMAGE_OF_DAY_URL)
    suspend fun getImageOfDay(@Query("api_key") apiKey: String): PictureOfDay
}

object AsteroidApi {
    val service: AsteroidApiService by lazy { RetrofitInit.retrofit.create(AsteroidApiService::class.java) }
}