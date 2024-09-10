package com.example.mybrickset.domain.usecase

data class BricksetUseCases(
    val getNewReleasedSets: GetNewReleasedSets,
    val getSetsByTheme: GetSetsByTheme,
    val getThemes: GetThemes,
    val searchSets: SearchSets,
    val login: Login,
    val getAdditionalImage: GetAdditionalImage,
    val getSetById: GetSetById,
    val getReviews: GetReviews,
    val setCollectionWanted: SetCollectionWanted,
    val getSetsWanted: GetSetsWanted
)
