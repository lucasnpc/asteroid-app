package com.udacity.asteroidradar.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.domain.AsteroidRadarUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class FetchAsteroidsWork @AssistedInject constructor(
    @Assisted applicationContext: Context,
    @Assisted params: WorkerParameters,
    private val asteroidRadarUseCases: AsteroidRadarUseCases
) :
    CoroutineWorker(applicationContext, params) {
    companion object {
        const val WORK_NAME = "FetchAsteroidsWork"
    }

    override suspend fun doWork(): Result {
        return try {
            val list = asteroidRadarUseCases.getAsteroidUseCase()
            if (list.isNotEmpty()) {
                asteroidRadarUseCases.saveAsteroidsUseCase(list)
            }
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}