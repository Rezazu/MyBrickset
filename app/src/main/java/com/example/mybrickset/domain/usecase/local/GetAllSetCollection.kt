package com.example.mybrickset.domain.usecase.local

import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.domain.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllSetCollection (
    private val localRepository: LocalRepository
) {
     operator fun invoke(): Flow<List<SetCollection>> {
        return localRepository.getAllSetCollection()
    }
}