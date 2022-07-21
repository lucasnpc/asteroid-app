package com.udacity.asteroidradar.data.api.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidDto(
    val id: String,
    @Json(name = "name")
    val codename: String,
    @Json(name = "close_approach_data")
    val closeAproachData: List<CloseAproachData>,
    @Json(name = "absolute_magnitude_h")
    val absoluteMagnitude: Double,
    @Json(name = "estimated_diameter")
    val estimatedDiameterData: EstimatedDiameterData,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
) : Parcelable

@Parcelize
data class CloseAproachData(
    @Json(name = "close_approach_date")
    val closeApproachDate: String,
    @Json(name = "relative_velocity")
    val relativeVelocity: RelativeVelocityData,
    @Json(name = "miss_distance")
    val missDistanceToEarth: MissDistance
) : Parcelable

@Parcelize
data class RelativeVelocityData(
    @Json(name = "kilometers_per_second") val kilometersPerSecond: Double,
) : Parcelable

@Parcelize
data class MissDistance(
    val astronomical: Double
) : Parcelable

@Parcelize
data class EstimatedDiameterData(
    @Json(name = "kilometers")
    val diameterInMeters: Diameter
) : Parcelable

@Parcelize
data class Diameter(
    @Json(name = "estimated_diameter_min")
    val estimatedDiameterMin: Double,
    @Json(name = "estimated_diameter_max")
    val estimatedDiameterMax: Double
) : Parcelable
