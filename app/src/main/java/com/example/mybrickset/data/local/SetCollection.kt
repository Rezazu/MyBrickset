package com.example.mybrickset.data.local

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybrickset.data.remote.dto.getsets.AgeRange
import com.example.mybrickset.data.remote.dto.getsets.Barcode
import com.example.mybrickset.data.remote.dto.getsets.Collections
import com.example.mybrickset.data.remote.dto.getsets.Collection
import com.example.mybrickset.data.remote.dto.getsets.Dimensions
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import java.math.BigDecimal
import java.util.Date

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