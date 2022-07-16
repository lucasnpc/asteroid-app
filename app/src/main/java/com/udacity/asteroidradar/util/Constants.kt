package com.udacity.asteroidradar.util

object Constants {
    private const val API_KEY = "qQntkNKT2YfZZ4ZaNcuAgwinhuRewVtqPzhiZhCK"
    private const val BASE_URL = "https://api.nasa.gov/"
    const val ASTEROIDS_URL = "${BASE_URL}neo/rest/v1/feed"
    const val IMAGE_OF_DAY_URL = "${BASE_URL}planetary/apod"
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
}