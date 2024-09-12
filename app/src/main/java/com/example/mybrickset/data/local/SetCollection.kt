package com.example.mybrickset.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "set_collection")
data class SetCollection(
    val number: String,
    val numberVariant: Int,
    val image: String,
    val name: String,

    val condition: String,
    val acquiredDate: String,
    val price: Double,

    @PrimaryKey(autoGenerate = true)
    val setId : Int? = null,
)