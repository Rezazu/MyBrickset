package com.example.mybrickset.domain.usecase.local

import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.domain.LocalRepository

class InsertSetCollection(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(setCollection: SetCollection) {
        return localRepository.insertSetCollection(setCollection)
    }
}