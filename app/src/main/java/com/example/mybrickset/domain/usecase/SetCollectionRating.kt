package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import com.example.mybrickset.domain.BricksetRepository

class SetCollectionRating(
    private val bricksetRepository: BricksetRepository
) {
    suspend operator fun invoke(setId: Int, rating: Int): SetCollectionResponse {
        return bricksetRepository.setCollectionRating(setId, rating)
    }
}