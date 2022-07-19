package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.data.api.util.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid

class GetAsteroidUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(): Collection<List<Asteroid>> {
        return try {
            val asteroids = repository.getAsteroids(
                startDate = getNextSevenDaysFormattedDates().first(),
                endDate = getNextSevenDaysFormattedDates().last()
            )
            asteroids.nearEarthObjects.values
        } catch (e: Exception) {
            println(e.message.toString())
            listOf()
        }
    }
}
