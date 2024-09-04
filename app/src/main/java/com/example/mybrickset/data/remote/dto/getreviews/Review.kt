package com.example.mybrickset.data.remote.dto.getreviews

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val HTML: Boolean,
    val author: String,
    val datePosted: String,
    val rating: Rating,
    val review: String,
    val title: String
)