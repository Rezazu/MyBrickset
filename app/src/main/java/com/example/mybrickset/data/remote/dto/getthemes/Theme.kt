package com.example.mybrickset.data.remote.dto.getthemes

data class Theme(
    val setCount: Int,
    val subthemeCount: Int,
    val theme: String,
    val yearFrom: Int,
    val yearTo: Int
)