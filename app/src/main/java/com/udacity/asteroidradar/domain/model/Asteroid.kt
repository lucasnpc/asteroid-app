package com.udacity.asteroidradar.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Asteroid(
    val id: String,
    @SerializedName("name")
    val codename: String,
    val closeApproachDate: String,
    @SerializedName("absolute_magnitude_h") val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    @SerializedName("relative_velocity") val relativeVelocity: Double,
    val distanceFromEarth: Double,
    @SerializedName("is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
) : Parcelable
