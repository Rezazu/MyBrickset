package com.example.mybrickset.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybrickset.data.remote.dto.getsets.AgeRange
import com.example.mybrickset.data.remote.dto.getsets.Barcode
import com.example.mybrickset.data.remote.dto.getsets.Collections
import com.example.mybrickset.data.remote.dto.getsets.Collection
import com.example.mybrickset.data.remote.dto.getsets.Dimensions
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom

//@Entity(tableName = "set_collection")
//data class SetCollection(
//    @PrimaryKey(autoGenerate = false)
//    val setID: Int,
//    val LEGOCom: LEGOCom,
//    val additionalImageCount: Int,
//    val ageRange: AgeRange,
//    val availability: String,
//    val barcode: Barcode,
//    val bricksetURL: String,
//    val category: String,
//    val collection: Collection,
//    val collections: Collections,
//    val dimensions: Dimensions,
//    val image: Image,
//    val instructionsCount: Int,
//    val lastUpdated: String,
//    val minifigs: Int,
//    val name: String,
//    val number: String,
//    val numberVariant: Int,
//    val packagingType: String,
//    val pieces: Int,
//    val rating: Double,
//    val released: Boolean,
//    val reviewCount: Int,
//    val subtheme: String,
//    val theme: String,
//    val themeGroup: String,
//    val year: Int
//)