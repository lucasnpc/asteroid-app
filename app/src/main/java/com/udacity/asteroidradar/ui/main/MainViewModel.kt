package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.domain.AsteroidRadarUseCases
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.domain.model.PictureOfDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val asteroidRadarUseCases: AsteroidRadarUseCases) :
    ViewModel() {

    private val _pictureOfDay = MutableStateFlow(PictureOfDay())
    val pictureOfDay: StateFlow<PictureOfDay> = _pictureOfDay

    private val _mainState = MutableStateFlow(MainFragmentState())
    val mainState: StateFlow<MainFragmentState> = _mainState

    private val _asteroids = MutableStateFlow<List<Asteroid>>(listOf())
    val asteroids: StateFlow<List<Asteroid>> = _asteroids

    init {
        getImageOfDay()
        getAsteroids()
    }

    fun getAsteroids() {
        viewModelScope.launch(Dispatchers.IO) {
            _mainState.emit(MainFragmentState(loading = true))
            val list = asteroidRadarUseCases.getAsteroidUseCase()
            if (list.isNotEmpty()) {
                asteroidRadarUseCases.saveAsteroidsUseCase(list)
                _mainState.emit(MainFragmentState(asteroids = list))
                return@launch
            }
            _mainState.emit(MainFragmentState(failed = true))
        }
    }

    private fun getImageOfDay() {
        viewModelScope.launch {
            _pictureOfDay.emit(asteroidRadarUseCases.getImageOfDayUseCase())
        }
    }
}