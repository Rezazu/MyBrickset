package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetNewReleasedSets(
    private val bricksetRepository: BricksetRepository
) {
    operator fun invoke(): Flow<Result<List<Set>>> = flow{
        try {
            emit(Result.Loading)
            val sets = bricksetRepository.getNewReleasedSets().sets.filterNot {
                it.name.contains("{?}", ignoreCase = true)
            }
            emit(Result.Success(sets))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Result.Error("So unicivilized (No Connection!)"))
        }
    }
}