package com.example.mybrickset.data.repository

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class BricksetRepositoryImplementation @Inject constructor(
    private val bricksetApi: BricksetApi
): BricksetRepository {

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)

    override suspend fun getNewReleasedSets(): SetsResponse {
        return bricksetApi.getSets(userHash ="", params = "{'year':'$currentYear','orderBy':'Random','pageSize':30}")
    }

    override suspend fun getThemes(): ThemesResponse {
        return bricksetApi.getTheme()
    }
}