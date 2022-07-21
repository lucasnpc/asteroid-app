package com.udacity.asteroidradar.ui.main

import com.udacity.asteroidradar.domain.model.Asteroid

data class MainFragmentState(
    val asteroids: List<Asteroid> = listOf(),
    val failed: Boolean = false,
    val loading: Boolean = false
)
