package com.udacity.asteroidradar.data

import com.udacity.asteroidradar.data.api.AsteroidApiService
import com.udacity.asteroidradar.data.api.AsteroidResponse
import com.udacity.asteroidradar.data.local.AsteroidDatabase
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.PictureOfDay
import com.udacity.asteroidradar.util.Constants

class AsteroidRadarRepositoryImpl(
    private val service: AsteroidApiService,
    private val database: AsteroidDatabase
) : AsteroidRadarRepository {
    override suspend fun getAsteroids(
        startDate: String,
        endDate: String
    ): AsteroidResponse =
        service.getAsteroids(
            startDate = "2022-07-07",
            endDate = "2022-07-14",
            apiKey = Constants.API_KEY
        )

    override suspend fun getImageDay(): PictureOfDay =
        service.getImageOfDay(Constants.API_KEY)

}