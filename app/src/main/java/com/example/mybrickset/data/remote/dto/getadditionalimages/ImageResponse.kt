package com.example.mybrickset.data.remote.dto.getadditionalimages

data class ImageResponse(
    val additionalImages: List<AdditionalImage>,
    val matches: Int,
    val status: String
)