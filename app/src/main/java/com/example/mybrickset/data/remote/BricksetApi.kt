package com.example.mybrickset.data.remote

import com.example.mybrickset.const.API_KEY
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BricksetApi {

    @GET("getSets")
    suspend fun getSets(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("userHash") userHash: String?,
        @Query("params") params:String,
    ):SetsResponse
}