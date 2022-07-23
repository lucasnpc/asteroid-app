package com.udacity.asteroidradar.data.api.util

import com.udacity.asteroidradar.data.api.dto.AsteroidDto
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.util.Constants
import java.text.SimpleDateFormat
import java.util.*

fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calendar = Calendar.getInstance()
    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        formattedDateList.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, -1)
    }

    return formattedDateList
}

fun convertToAsteroidList(
    asteroids: List<AsteroidDto>,
    array: ArrayList<Asteroid>
): Unit = asteroids.forEach { asteroid ->
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
                relativeVelocity = closeAproachData[0].relativeVelocity.kilometersPerSecond,
                distanceFromEarth = closeAproachData[0].missDistanceToEarth.astronomical,
                isPotentiallyHazardous = isPotentiallyHazardous
            )
        )
    }
}