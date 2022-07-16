package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.api.AsteroidApi
import com.udacity.asteroidradar.util.Constants.API_KEY
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getAsteroids() {
        viewModelScope.launch {
            try {
                val asteroids = AsteroidApi.service.getAsteroids(
                    startDate = "2022-07-07",
                    endDate = "2022-07-14",
                    apiKey = API_KEY
                )
                println(asteroids)
            } catch (e: Exception) {
                println(e.message.toString())
            }
        }
    }
}