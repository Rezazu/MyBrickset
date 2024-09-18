package com.example.mybrickset.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetCollection(setCollection: SetCollection)

    @Delete
    suspend fun deleteSetCollection(setCollection: SetCollection)

    @Query("SELECT * FROM set_collection")
    fun getAllSetCollection(): Flow<List<SetCollection>>

    @Query("SELECT * FROM set_collection ORDER BY price ASC")
    fun getAllSetCollectionOrderByPriceAsc(): Flow<List<SetCollection>>

    @Query("SELECT * FROM set_collection ORDER BY price DESC")
    fun getAllSetCollectionOrderByPriceDesc(): Flow<List<SetCollection>>

    @Query("SELECT * FROM set_collection ORDER BY acquiredDate ASC")
    fun getAllSetCollectionOrderByDateAsc(): Flow<List<SetCollection>>

    @Query("SELECT * FROM set_collection ORDER BY acquiredDate DESC")
    fun getAllSetCollectionOrderByDateDesc(): Flow<List<SetCollection>>

    @Query("SELECT SUM(PRICE) FROM set_collection")
    fun getSumPrice(): Flow<Double>

    @Query("SELECT COUNT(SETID) FROM set_collection")
    fun getSetCount(): Flow<Int>
}