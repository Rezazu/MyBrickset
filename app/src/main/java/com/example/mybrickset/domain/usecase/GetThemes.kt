package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetThemes (
   private val bricksetRepository: BricksetRepository
) {
    operator fun invoke(): Flow<Result<List<Theme>>> = flow {
        try {
            emit(Result.Loading)
            val sets = bricksetRepository.getThemes().themes
                .filterNot {
                    it.theme.contains("{unknown}", ignoreCase = true)
                }
                .filter {
                    it.yearTo.toString().contains("2024")
                }
            emit(Result.Success(sets))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Result.Error("So unicivilized (No Connection!)"))
        }
    }
}