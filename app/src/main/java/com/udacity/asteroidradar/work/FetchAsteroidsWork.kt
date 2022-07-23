package com.udacity.asteroidradar.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.api.util.convertToAsteroidList
import com.udacity.asteroidradar.data.api.util.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.model.Asteroid
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class FetchAsteroidsWork @AssistedInject constructor(
    @Assisted applicationContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: AsteroidRadarRepository
) :
    CoroutineWorker(applicationContext, params) {
    companion object {
        const val WORK_NAME = "FetchAsteroidsWork"
    }

    override suspend fun doWork(): Result {
        return try {
            val array: ArrayList<Asteroid> = ArrayList()
            repository.getAsteroids(
                startDate = getNextSevenDaysFormattedDates().first(),
                endDate = getNextSevenDaysFormattedDates().last()
            ).nearEarthObjects.values.forEach { asteroids ->
                convertToAsteroidList(asteroids, array)
            }
            if (array.isNotEmpty()) {
                repository.saveAsteroids(array)
            }
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}