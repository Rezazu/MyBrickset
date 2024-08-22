package com.example.mybrickset.domain

import com.example.mybrickset.data.local.SetCollection
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertSetCollection(setCollection: SetCollection)
    suspend fun deleteSetCollection(setCollection: SetCollection)
    fun getAllSetCollection(): Flow<List<SetCollection>>
}