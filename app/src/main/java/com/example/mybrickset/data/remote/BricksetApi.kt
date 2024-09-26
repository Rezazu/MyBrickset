package com.example.mybrickset.data.remote

import com.example.mybrickset.BuildConfig
import com.example.mybrickset.data.remote.dto.getadditionalimages.ImageResponse
import com.example.mybrickset.data.remote.dto.getreviews.ReviewsResponse
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.data.remote.dto.getusernotes.UserNotesResponse
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BricksetApi {

    @GET("getSets")
    suspend fun getSets(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("userHash") userHash: String?,
        @Query("params") params:String,
    ): SetsResponse

    @GET("getThemes")
    suspend fun getTheme(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): ThemesResponse

    @GET("login")
    suspend fun login(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("username") username: String,
        @Query("password") password: String
    ): LoginResponse

    @GET("getAdditionalImages")
    suspend fun getAdditionalImages(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("setID") setId: Int
    ): ImageResponse

    @GET("getReviews")
    suspend fun getReviews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("setID") setId: Int
    ): ReviewsResponse

    @GET("setCollection")
    suspend fun setCollection(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("userHash") userHash: String?,
        @Query("setID") setId: Int,
        @Query("params") params:String
    ): SetCollectionResponse

    @GET("getUserNotes")
    suspend fun GetUserNotes(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("userHash") userHash: String?,
    ): UserNotesResponse
}