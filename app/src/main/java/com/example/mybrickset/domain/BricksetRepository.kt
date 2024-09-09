package com.example.mybrickset.domain

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getadditionalimages.ImageResponse
import com.example.mybrickset.data.remote.dto.getreviews.ReviewsResponse
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BricksetRepository {
    suspend fun getNewReleasedSets(): SetsResponse
    suspend fun getSetsByTheme(theme: String, pageSize: String): SetsResponse
    suspend fun getThemes(): ThemesResponse
    suspend fun searchSets(query: String): SetsResponse
    suspend fun getCollection(): SetsResponse
    suspend fun login(username: String, password: String): LoginResponse
    suspend fun getAdditionalImage(setId: Int): ImageResponse
    suspend fun getSetById(setId: Int): SetsResponse
    suspend fun getReviews(setId: Int): ReviewsResponse
    suspend fun setCollectionWanted(setId: Int, isWanted: Int): SetCollectionResponse
}