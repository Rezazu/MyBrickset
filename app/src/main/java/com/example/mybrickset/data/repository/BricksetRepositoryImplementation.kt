package com.example.mybrickset.data.repository

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class BricksetRepositoryImplementation @Inject constructor(
    private val bricksetApi: BricksetApi
): BricksetRepository {

    override suspend fun getNewReleasedSets(): SetsResponse {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return bricksetApi.getSets(userHash ="", params = "{'year':$currentYear,'pageSize':30}")
    }
//    {'theme':'star wars','year':'2019,2024'}
//    {'year':'2024','pageSize':4}
}