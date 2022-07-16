package com.udacity.asteroidradar

import com.udacity.asteroidradar.data.api.AsteroidApi
import com.udacity.asteroidradar.data.api.util.parseAsteroidsJsonResult
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.util.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RetrofitApiTest {

    @Test
    fun `Assert that retrofit call is returning data`() = runBlocking {
        val asteroids = AsteroidApi.service.getAsteroids(
            startDate = "2022-07-07",
            endDate = "2022-07-14",
            apiKey = Constants.API_KEY
        )
//        println(asteroids.toString())

        assert(parseAsteroidsJsonResult(asteroids) == listOf<Asteroid>())
    }
}