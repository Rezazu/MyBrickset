package com.example.mybrickset.data.remote.dto.getthemes

import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    val setCount: Int,
    val subthemeCount: Int,
    val theme: String,
    val yearFrom: Int,
    val yearTo: Int
)