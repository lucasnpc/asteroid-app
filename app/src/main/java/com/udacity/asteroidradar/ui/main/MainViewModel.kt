package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.api.AsteroidApi
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.domain.model.PictureOfDay
import com.udacity.asteroidradar.util.Constants.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

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
            try {
                val asteroids = AsteroidApi.service.getAsteroids(
                    startDate = "2022-07-07",
                    endDate = "2022-07-14",
                    apiKey = API_KEY
                )
                _asteroids.emit(asteroids.nearEarthObjects.values)
            } catch (e: Exception) {
                println(e.message.toString())
            }
        }
    }

    private fun getImageOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.emit(AsteroidApi.service.getImageOfDay(API_KEY))
            } catch (e: Exception) {
                println(e.message.toString())
            }
        }
    }
}