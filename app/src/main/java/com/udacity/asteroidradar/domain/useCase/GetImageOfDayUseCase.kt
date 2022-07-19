package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.PictureOfDay

class GetImageOfDayUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(): PictureOfDay {
        return try {
            repository.getImageDay()
        } catch (e: Exception) {
            println(e.message.toString())
            PictureOfDay()
        }
    }
}