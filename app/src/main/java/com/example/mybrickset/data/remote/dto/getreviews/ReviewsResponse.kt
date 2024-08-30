package com.example.mybrickset.data.remote.dto.getreviews

data class ReviewsResponse(
    val matches: Int,
    val reviews: List<Review>,
    val status: String
)