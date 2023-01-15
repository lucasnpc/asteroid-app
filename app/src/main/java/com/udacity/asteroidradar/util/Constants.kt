package com.udacity.asteroidradar.util

import android.net.Uri

object Constants {
    const val API_KEY = "qQntkNKT2YfZZ4ZaNcuAgwinhuRewVtqPzhiZhCK"
    const val BASE_URL = "https://api.nasa.gov/"
    const val ASTEROIDS_URL = "neo/rest/v1/feed"
    const val IMAGE_OF_DAY_URL = "planetary/apod"
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val AUTHORITY = "com.udacity.asteroidradar.provider"
    val PROVIDER_URL: Uri = Uri.parse("content://$AUTHORITY/asteroids")
}