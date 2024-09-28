package com.example.mybrickset.data.repository

import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.remote.dto.getadditionalimages.ImageResponse
import com.example.mybrickset.data.remote.dto.getreviews.ReviewsResponse
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.data.remote.dto.getusernotes.UserNotesResponse
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.data.remote.dto.setcollection.SetCollectionResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.Calendar
import javax.inject.Inject

class BricksetRepositoryImplementation @Inject constructor(
    private val bricksetApi: BricksetApi,
    private val pref: AuthPreferences
): BricksetRepository {

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)

    private val hash: Flow<String> = pref.getUserHash()

    override suspend fun login(username: String, password: String): LoginResponse {
        return bricksetApi.login(username = username, password = password)
    }

    override suspend fun getNewReleasedSets(): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'year':'$currentYear','orderBy':'Random','pageSize':30}")
    }

    override suspend fun getThemes(): ThemesResponse {
        return bricksetApi.getTheme()
    }

    override suspend fun getSetsByTheme(theme: String, pageSize: String): SetsResponse { //'pageSize':30
        return bricksetApi.getSets(userHash = hash.first(), params = "{'theme':'$theme','year': '${currentYear-1},$currentYear','orderBy':'Random'$pageSize}")
    }

    override suspend fun searchSets(query: String): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'query':'$query'}")
    }

    override suspend fun getCollection(): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'owned':'1'}")
    }

    override suspend fun getSetsWanted(): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'wanted':1}")
    }

    override suspend fun getSetsOwned(): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'owned':1}")
    }

    override suspend fun getAdditionalImage(setId: Int): ImageResponse {
        return bricksetApi.getAdditionalImages(setId = setId)
    }

    override suspend fun getSetById(setId: Int): SetsResponse {
        return bricksetApi.getSets(userHash = hash.first(), params = "{'setID':$setId}")
    }

    override suspend fun getReviews(setId: Int): ReviewsResponse {
        return bricksetApi.getReviews(setId = setId)
    }

    override suspend fun setCollectionWanted(setId: Int, isWanted: Int): SetCollectionResponse {
        return bricksetApi.setCollection(userHash = hash.first(), setId = setId, params = "{'want':$isWanted}")
    }

    override suspend fun setCollectionOwned(setId: Int, isOwned: Int): SetCollectionResponse {
        return bricksetApi.setCollection(userHash = hash.first(), setId = setId, params = "{'own':$isOwned}")
    }

    override suspend fun setCollectionNotes(setId: Int, notes: String): SetCollectionResponse {
        return bricksetApi.setCollection(userHash = hash.first(), setId = setId, params = "{'notes':'$notes'}")
    }

    override suspend fun setCollectionRating(setId: Int, rating: Int): SetCollectionResponse {
        return bricksetApi.setCollection(userHash = hash.first(), setId = setId, params = "{'rating':'$rating'}")
    }

    override suspend fun getUserNotes(): UserNotesResponse {
        return bricksetApi.GetUserNotes(userHash = hash.first())
    }


}