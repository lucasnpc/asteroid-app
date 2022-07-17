package com.udacity.asteroidradar.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureOfDay(
    @SerialName("media_type") val mediaType: String = "",
    val title: String = "",
    val url: String = ""
)