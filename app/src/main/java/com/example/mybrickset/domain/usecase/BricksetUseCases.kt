package com.example.mybrickset.domain.usecase

data class BricksetUseCases(
    val getNewReleasedSets: GetNewReleasedSets,
    val getSetsByTheme: GetSetsByTheme,
    val getThemes: GetThemes,
    val searchSets: SearchSets
)
