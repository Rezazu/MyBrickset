package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import com.example.mybrickset.domain.BricksetRepository

class SetCollectionWanted(
    private val bricksetRepository: BricksetRepository
) {
    suspend operator fun invoke(setId: Int, isWanted: Int): SetCollectionResponse {
        return bricksetRepository.setCollectionWanted(setId, isWanted)
    }
}