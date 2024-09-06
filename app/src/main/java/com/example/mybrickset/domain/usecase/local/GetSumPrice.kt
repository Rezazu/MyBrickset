package com.example.mybrickset.domain.usecase.local

import com.example.mybrickset.domain.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetSumPrice(
    val localRepository: LocalRepository
) {
    operator fun invoke(): Flow<Double> {
        return localRepository.getSumPrice()
    }
}