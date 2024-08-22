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
}