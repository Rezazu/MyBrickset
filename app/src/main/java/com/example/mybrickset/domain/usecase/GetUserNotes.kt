package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.remote.dto.getusernotes.UserNotesResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserNotes(
    private val bricksetRepository: BricksetRepository,
) {
    operator fun invoke(): Flow<Resource<UserNotesResponse>> = flow {
        try {
            emit(Resource.Loading())
            val userNotes = bricksetRepository.getUserNotes()
            emit(Resource.Success(userNotes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Resource.Error("So unicivilized (No Connection!)"))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }    }
}