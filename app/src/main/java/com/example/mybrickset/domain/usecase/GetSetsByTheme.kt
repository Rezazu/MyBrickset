package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.SetsResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetSetsByTheme(
    private val bricksetRepository: BricksetRepository,
) {
    operator fun invoke(themes: String, count: Int): Flow<Result<SetsResponse>> = flow{
        try {
            emit(Result.Loading)
            val pageSize = if (count >= 1) ",'pageSize':$count" else ""
//            val sets = bricksetRepository.getSetsByTheme(theme = themes, pageSize = pageSize).sets.filterNot {
//                it.name.contains("{?}")
//            }
            val sets = bricksetRepository.getSetsByTheme(theme = themes, pageSize = pageSize)
            emit(Result.Success(sets))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Result.Error("So unicivilized (No Connection!)"))
        }
    }
}