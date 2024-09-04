package com.example.mybrickset.data.remote.dto.getreviews

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val buildingExperience: Int,
    val overall: Int,
    val parts: Int,
    val playability: Int,
    val valueForMoney: Int
)