package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid

class SaveAsteroidsUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(list: List<Asteroid>) {
        repository.saveAsteroids(list)
    }
}