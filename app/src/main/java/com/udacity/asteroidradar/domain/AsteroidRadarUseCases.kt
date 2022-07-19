package com.udacity.asteroidradar.domain

import com.udacity.asteroidradar.domain.useCase.GetAsteroidUseCase
import com.udacity.asteroidradar.domain.useCase.GetImageOfDayUseCase

data class AsteroidRadarUseCases(
    val getImageOfDayUseCase: GetImageOfDayUseCase,
    val getAsteroidUseCase: GetAsteroidUseCase
)
