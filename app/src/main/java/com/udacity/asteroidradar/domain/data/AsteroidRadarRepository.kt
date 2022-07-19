package com.udacity.asteroidradar.domain.data

import com.udacity.asteroidradar.data.api.AsteroidResponse
import com.udacity.asteroidradar.domain.model.PictureOfDay

interface AsteroidRadarRepository {
    suspend fun getAsteroids(
        startDate: String,
        endDate: String
    ): AsteroidResponse

    suspend fun getImageDay(): PictureOfDay
}