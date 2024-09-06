package com.example.mybrickset.domain.usecase.local

import com.example.mybrickset.domain.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetSetCount(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Flow<Int> {
        return localRepository.getSetCount()
    }
}