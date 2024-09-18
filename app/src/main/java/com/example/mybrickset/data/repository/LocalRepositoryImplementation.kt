package com.example.mybrickset.data.repository

import com.example.mybrickset.data.local.CollectionDao
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImplementation @Inject constructor(
    private val collectionDao: CollectionDao
): LocalRepository {
    override suspend fun insertSetCollection(setCollection: SetCollection) {
        collectionDao.insertSetCollection(setCollection)
    }

    override suspend fun deleteSetCollection(setCollection: SetCollection) {
        collectionDao.deleteSetCollection(setCollection)
    }

    override fun getAllSetCollection(): Flow<List<SetCollection>> {
        return collectionDao.getAllSetCollection()
    }

    override fun getAllSetCollectionOrderByPriceAsc(): Flow<List<SetCollection>> {
        return collectionDao.getAllSetCollectionOrderByPriceAsc()
    }

    override fun getAllSetCollectionOrderByPriceDesc(): Flow<List<SetCollection>> {
        return collectionDao.getAllSetCollectionOrderByPriceDesc()
    }

    override fun getAllSetCollectionOrderByDateAsc(): Flow<List<SetCollection>> {
        return collectionDao.getAllSetCollectionOrderByDateAsc()
    }

    override fun getAllSetCollectionOrderByDateDesc(): Flow<List<SetCollection>> {
        return collectionDao.getAllSetCollectionOrderByDateDesc()
    }


    override fun getSumPrice(): Flow<Double> {
        return collectionDao.getSumPrice()
    }

    override fun getSetCount(): Flow<Int> {
        return collectionDao.getSetCount()
    }
}