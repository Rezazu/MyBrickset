package com.example.mybrickset.domain

import com.example.mybrickset.data.local.SetCollection
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertSetCollection(setCollection: SetCollection)
    suspend fun deleteSetCollection(setCollection: SetCollection)
    fun getAllSetCollection(): Flow<List<SetCollection>>
    fun getAllSetCollectionOrderByPriceAsc(): Flow<List<SetCollection>>
    fun getAllSetCollectionOrderByPriceDesc(): Flow<List<SetCollection>>
    fun getAllSetCollectionOrderByDateAsc(): Flow<List<SetCollection>>
    fun getAllSetCollectionOrderByDateDesc(): Flow<List<SetCollection>>
    fun getSumPrice(): Flow<Double>
    fun getSetCount(): Flow<Int>

}