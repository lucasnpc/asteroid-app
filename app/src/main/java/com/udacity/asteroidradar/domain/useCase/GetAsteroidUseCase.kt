package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.data.api.util.convertToAsteroidList
import com.udacity.asteroidradar.data.api.util.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid

class GetAsteroidUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(): List<Asteroid> {
        return try {
            val array: ArrayList<Asteroid> = ArrayList()
            repository.getAsteroids(
                startDate = getNextSevenDaysFormattedDates().first(),
                endDate = getNextSevenDaysFormattedDates().last()
            ).nearEarthObjects.values.forEach { asteroids ->
                convertToAsteroidList(asteroids, array)
            }
            array
        } catch (e: Exception) {
            println(e.message.toString())
            listOf()
        }
    }
}
