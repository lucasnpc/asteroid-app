package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.domain.AsteroidRadarUseCases
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.domain.model.PictureOfDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val asteroidRadarUseCases: AsteroidRadarUseCases) :
    ViewModel() {

    private val _pictureOfDay = MutableStateFlow(PictureOfDay())
    val pictureOfDay: StateFlow<PictureOfDay> = _pictureOfDay

    private val _asteroids = MutableStateFlow<Collection<List<Asteroid>>>(listOf())
    val asteroids: StateFlow<Collection<List<Asteroid>>> = _asteroids

    init {
        getImageOfDay()
        getAsteroids()
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            _asteroids.emit(asteroidRadarUseCases.getAsteroidUseCase())
        }
    }

    private fun getImageOfDay() {
        viewModelScope.launch {
            _pictureOfDay.emit(asteroidRadarUseCases.getImageOfDayUseCase())
        }
    }
}