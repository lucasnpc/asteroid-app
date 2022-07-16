package com.udacity.asteroidradar.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageOfDayResponse(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("service_version")
    val serviceVersion: String,
    val title: String,
    val url: String
)