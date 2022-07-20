package com.udacity.asteroidradar.data.api

import com.google.gson.annotations.SerializedName
import com.udacity.asteroidradar.data.api.dto.AsteroidDto
import kotlinx.serialization.Serializable

@Serializable
data class AsteroidResponse(
    val links: Link,
    @SerializedName("element_count") val elementCount: Int,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)

@Serializable
data class Link(
    val next: String,
    val prev: String,
    val self: String
)
