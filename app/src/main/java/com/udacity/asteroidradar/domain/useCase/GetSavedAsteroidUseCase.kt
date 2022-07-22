package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid

class GetSavedAsteroidUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(): List<Asteroid> {
        return try {
            repository.getSavedAsteroids().sortedByDescending { it.closeApproachDate }
        } catch (e: Exception) {
            println(e.message.toString())
            listOf()
        }
    }
}