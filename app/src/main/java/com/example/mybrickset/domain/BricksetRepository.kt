package com.example.mybrickset.domain

import com.example.mybrickset.data.remote.dto.getadditionalimages.ImageResponse
import com.example.mybrickset.data.remote.dto.getreviews.ReviewsResponse
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.data.remote.dto.getusernotes.UserNotesResponse
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse

interface BricksetRepository {
    suspend fun getNewReleasedSets(): SetsResponse
    suspend fun getSetsByTheme(theme: String, pageSize: String): SetsResponse
    suspend fun getThemes(): ThemesResponse
    suspend fun searchSets(query: String): SetsResponse
    suspend fun getCollection(): SetsResponse
    suspend fun getSetsWanted(): SetsResponse
    suspend fun getSetsOwned(): SetsResponse
    suspend fun login(username: String, password: String): LoginResponse
    suspend fun getAdditionalImage(setId: Int): ImageResponse
    suspend fun getSetById(setId: Int): SetsResponse
    suspend fun getReviews(setId: Int): ReviewsResponse
    suspend fun setCollectionWanted(setId: Int, isWanted: Int): SetCollectionResponse
    suspend fun setCollectionOwned(setId: Int, isOwned: Int): SetCollectionResponse
    suspend fun setCollectionNotes(setId: Int, notes: String): SetCollectionResponse
    suspend fun setCollectionRating(setId: Int, rating: Int): SetCollectionResponse
    suspend fun getUserNotes(): UserNotesResponse
}