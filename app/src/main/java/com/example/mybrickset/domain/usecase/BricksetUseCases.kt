package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.remote.dto.getadditionalimages.AdditionalImage

data class BricksetUseCases(
    val getNewReleasedSets: GetNewReleasedSets,
    val getSetsByTheme: GetSetsByTheme,
    val getThemes: GetThemes,
    val searchSets: SearchSets,
    val login: Login,
    val getAdditionalImage: GetAdditionalImage,
    val getSetById: GetSetById
)
