package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import com.example.mybrickset.domain.BricksetRepository

class SetCollectionNotes(
    private val bricksetRepository: BricksetRepository
) {
    suspend operator fun invoke(setId: Int, notes: String): SetCollectionResponse {
        return bricksetRepository.setCollectionNotes(setId, notes)
    }
}