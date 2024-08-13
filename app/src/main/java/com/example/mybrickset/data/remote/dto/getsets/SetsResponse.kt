package com.example.mybrickset.data.remote.dto.getsets

data class SetsResponse(
    val matches: Int,
    val sets: List<Set>,
    val status: String
)