package com.example.mybrickset.domain

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BricksetRepository {

    suspend fun getSets(
        theme: String
    ): SetsResponse
}