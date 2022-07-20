package com.udacity.asteroidradar.data.api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AsteroidDto(
    val id: String,
    @SerializedName("name")
    val codename: String,
    @SerializedName("close_approach_data")
    val closeAproachData: List<CloseAproachData>,
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitude: Double,
    @SerializedName("estimated_diameter")
    val estimatedDiameterData: EstimatedDiameterData,
    @SerializedName("is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
) : Parcelable

@Parcelize
@Serializable
data class CloseAproachData(
    @SerializedName("close_approach_date")
    val closeApproachDate: String,
    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocityData,
    @SerializedName("miss_distance")
    val missDistanceToEarth: MissDistance
) : Parcelable

@Parcelize
@Serializable
data class RelativeVelocityData(
    @SerializedName("kilometers_per_second") val kilometersPerSecond: Double,
) : Parcelable

@Parcelize
@Serializable
data class MissDistance(
    val astronomical: Double
) : Parcelable

@Parcelize
@Serializable
data class EstimatedDiameterData(
    @SerializedName("kilometers")
    val diameterInMeters: Diameter
) : Parcelable

@Parcelize
@Serializable
data class Diameter(
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double,
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double
) : Parcelable
