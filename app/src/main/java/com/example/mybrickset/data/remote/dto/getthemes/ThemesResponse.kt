package com.example.mybrickset.data.remote.dto.getthemes

data class ThemesResponse(
    val matches: Int,
    val status: String,
    val themes: List<Theme>
)