package com.udacity.asteroidradar.data.api

import com.squareup.moshi.Json
import com.udacity.asteroidradar.data.api.dto.AsteroidDto

data class AsteroidResponse(
    val links: Link,
    @Json(name = "element_count") val elementCount: Int,
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)

data class Link(
    val next: String,
    val previous: String,
    val self: String
)
