package com.example.mybrickset.data.remote.dto.getsets

data class Collection(
    val notes: String,
    val owned: Boolean,
    val qtyOwned: Int,
    val rating: Int,
    val wanted: Boolean
)