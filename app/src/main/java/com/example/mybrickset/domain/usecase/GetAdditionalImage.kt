package com.example.mybrickset.domain.usecase

import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.domain.BricksetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAdditionalImage(
    private val bricksetRepository: BricksetRepository
) {
    operator fun invoke(setId: Int): Flow<Resource<List<Image>>> = flow{
        try {
            emit(Resource.Loading())
            val images = bricksetRepository.getAdditionalImage(setId).additionalImages
            emit(Resource.Success(images))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error, but a welcome one"))
        } catch (e: IOException) {
            emit(Resource.Error("So unicivilized (No Connection!)"))
        }
    }
}