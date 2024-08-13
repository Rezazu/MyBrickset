package com.example.mybrickset.data.remote.dto.getsets

data class Set(
    val LEGOCom: LEGOCom,
    val additionalImageCount: Int,
    val ageRange: AgeRange,
    val availability: String,
    val bricksetURL: String,
    val category: String,
    val dimensions: Dimensions,
    val image: Image,
    val instructionsCount: Int,
    val lastUpdated: String,
    val minifigs: Int,
    val name: String,
    val number: String,
    val numberVariant: Int,
    val packagingType: String,
    val pieces: Int,
    val rating: Double,
    val released: Boolean,
    val reviewCount: Int,
    val setID: Int,
    val subtheme: String,
    val theme: String,
    val themeGroup: String,
    val year: Int
)