package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.login.LoginResponse
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class Login(
    private val bricksetRepository: BricksetRepository
) {
    operator fun invoke(username: String, password: String): Flow<Result<LoginResponse>> = flow {
        try {
            emit(Result.Loading)
            val response =  bricksetRepository.login(username, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Result.Error("So unicivilized (No Connection!)"))
        }
    }
}