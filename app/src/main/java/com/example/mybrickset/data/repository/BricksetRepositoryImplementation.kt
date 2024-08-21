package com.example.mybrickset.data.repository

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.data.remote.dto.getthemes.ThemesResponse
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class BricksetRepositoryImplementation @Inject constructor(
    private val bricksetApi: BricksetApi
): BricksetRepository {

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)

    override suspend fun getNewReleasedSets(): SetsResponse {
        return bricksetApi.getSets(userHash ="", params = "{'year':'$currentYear','orderBy':'Random','pageSize':30}")
    }

    override suspend fun getThemes(): ThemesResponse {
        return bricksetApi.getTheme()
    }

    override suspend fun getSetsByTheme(theme: String): SetsResponse {
        return bricksetApi.getSets(userHash ="", params = "{'theme':'$theme','year': '${currentYear-1},$currentYear','orderBy':'Random','pageSize':30}")
    }

    override suspend fun searchSets(query: String): SetsResponse {
        return bricksetApi.getSets(userHash = "", params = "{'query':'$query'}")
    }

    override suspend fun getCollection(): SetsResponse {
        return bricksetApi.getSets(userHash = "tbxexiNQXt", params = "{'owned':'1'}")
    }

    override suspend fun login(username: String, password: String): LoginResponse {
        return bricksetApi.login(username = username, password = password)
    }
}