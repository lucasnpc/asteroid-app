package com.udacity.asteroidradar.domain

import com.udacity.asteroidradar.domain.useCase.*

data class AsteroidRadarUseCases(
    val getImageOfDayUseCase: GetImageOfDayUseCase,
    val getAsteroidUseCase: GetAsteroidUseCase,
    val saveAsteroidsUseCase: SaveAsteroidsUseCase,
    val getTodayAsteroidUseCase: GetTodayAsteroidUseCase,
    val getSavedAsteroidUseCase: GetSavedAsteroidUseCase
)
