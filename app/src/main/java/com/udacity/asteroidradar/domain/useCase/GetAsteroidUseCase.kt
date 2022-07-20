package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.data.api.dto.AsteroidDto
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

    private fun convertToAsteroidList(
        asteroids: List<AsteroidDto>,
        array: ArrayList<Asteroid>
    ) = asteroids.forEach { asteroid ->
        with(asteroid) {
            array.add(
                Asteroid(
                    id = id.toLong(),
                    codename = codename,
                    closeApproachDate = closeAproachData[0].closeApproachDate,
                    absoluteMagnitude = absoluteMagnitude,
                    estimatedDiameter = estimatedDiameterData.diameterInMeters.let { (diameterMin, diameterMax) ->
                        (diameterMax + diameterMin) / 2
                    },
                    relativeVelocity = closeAproachData[0].relativeVelocity.kilometersPerHour,
                    distanceFromEarth = closeAproachData[0].missDistanceToEarth.kilometers,
                    isPotentiallyHazardous = isPotentiallyHazardous
                )

            )
        }
    }
}
