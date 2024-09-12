package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetSetsByTheme(
    private val bricksetRepository: BricksetRepository,
) {
    operator fun invoke(themes: String, count: Int): Flow<Resource<SetsResponse>> = flow{
        try {
            emit(Resource.Loading())
            val pageSize = if (count >= 1) ",'pageSize':$count" else ""
            val sets = bricksetRepository.getSetsByTheme(theme = themes, pageSize = pageSize)
            emit(Resource.Success(sets))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Resource.Error("So unicivilized (No Connection!)"))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }
    }
}