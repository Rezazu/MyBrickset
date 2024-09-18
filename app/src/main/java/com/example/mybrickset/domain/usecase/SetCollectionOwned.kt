package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import com.example.mybrickset.domain.BricksetRepository

class SetCollectionOwned(
    private val bricksetRepository: BricksetRepository
) {
    suspend operator fun invoke(setId: Int, isOwned: Int): SetCollectionResponse {
        return bricksetRepository.setCollectionOwned(setId, isOwned)
    }
}