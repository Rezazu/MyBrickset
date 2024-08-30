package com.example.mybrickset.data.remote.dto.getadditionalimages

import com.example.mybrickset.data.remote.dto.getsets.Image

data class ImageResponse(
    val additionalImages: List<Image>,
    val matches: Int,
    val status: String
)