package com.example.mybrickset.domain

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BricksetRepository {
    suspend fun getNewReleasedSets(): SetsResponse
    suspend fun getSetsByTheme(theme: String): SetsResponse
    suspend fun getThemes(): ThemesResponse
    suspend fun searchSets(query: String): SetsResponse
}