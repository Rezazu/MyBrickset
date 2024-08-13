package com.example.mybrickset.data.repository

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BricksetRepositoryImplementation @Inject constructor(
    private val bricksetApi: BricksetApi
): BricksetRepository {

    override suspend fun getSets(
        theme: String
    ): SetsResponse {
        return bricksetApi.getSets(userHash = null, params = "'theme':$theme,'year':'2019,2024")
    }
//    {'theme':'star wars','year':'2019,2024'}
}