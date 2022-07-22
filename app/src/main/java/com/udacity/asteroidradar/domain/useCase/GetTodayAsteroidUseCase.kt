package com.udacity.asteroidradar.domain.useCase

import com.udacity.asteroidradar.data.api.util.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid

class GetTodayAsteroidUseCase(private val repository: AsteroidRadarRepository) {
    suspend operator fun invoke(): List<Asteroid> {
        return try {
            val array: ArrayList<Asteroid> = ArrayList()
            repository.getAsteroids(
                startDate = getNextSevenDaysFormattedDates().first(),
                endDate = getNextSevenDaysFormattedDates().last()
            ).nearEarthObjects.values.first().forEach { asteroidDto ->
                with(asteroidDto) {
                    array.add(
                        Asteroid(
                            id = id.toLong(),
                            codename = codename,
                            closeApproachDate = closeAproachData[0].closeApproachDate,
                            absoluteMagnitude = absoluteMagnitude,
                            estimatedDiameter = estimatedDiameterData.diameterInMeters.let { (diameterMin, diameterMax) ->
                                (diameterMax + diameterMin) / 2
                            },
                            relativeVelocity = closeAproachData[0].relativeVelocity.kilometersPerSecond,
                            distanceFromEarth = closeAproachData[0].missDistanceToEarth.astronomical,
                            isPotentiallyHazardous = isPotentiallyHazardous
                        )
                    )
                }
            }
            array
        } catch (e: Exception) {
            println(e.message.toString())
            listOf()
        }
    }
}