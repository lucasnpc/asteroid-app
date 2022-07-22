package com.udacity.asteroidradar.domain.data

import com.udacity.asteroidradar.data.api.AsteroidResponse
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.domain.model.PictureOfDay
import kotlinx.coroutines.flow.Flow

interface AsteroidRadarRepository {
    suspend fun getAsteroids(
        startDate: String,
        endDate: String
    ): AsteroidResponse

    suspend fun getImageDay(): PictureOfDay

    suspend fun saveAsteroids(list: List<Asteroid>)

    suspend fun getSavedAsteroids(): List<Asteroid>
}