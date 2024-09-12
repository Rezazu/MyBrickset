package com.example.mybrickset.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
): ViewModel() {

    private val _set: MutableStateFlow<Result<Set>> = MutableStateFlow(Result.Loading)
    val set: StateFlow<Result<Set>> get() = _set

    private val _images = MutableStateFlow(ImagesState())
    val images: StateFlow<ImagesState> = _images

    private val _reviews = MutableStateFlow(ReviewsState())
    val reviews: StateFlow<ReviewsState> = _reviews

    private fun getAdditionalImages(setId: Int) {
        bricksetUseCases.getAdditionalImage(setId).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _images.value = ImagesState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _images.value = ImagesState(isLoading = true)
                }
                is Resource.Success -> {
                    _images.value = ImagesState(images = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSetByID(setId: Int) {
        bricksetUseCases.getSetById(setId).onEach { result ->
            when(result) {
                is Result.Error -> {
                    _set.value = Result.Error(result.error)
                }
                is Result.Loading -> {
                    _set.value = Result.Loading
                }
                is Result.Success -> {
                    _set.value = Result.Success(result.data.sets[0])
                    getAdditionalImages(setId)
                    getReviews(setId)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getReviews(setId: Int) {
        bricksetUseCases.getReviews(setId).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _reviews.value = ReviewsState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _reviews.value = ReviewsState(isLoading = true)
                }
                is Resource.Success -> {
                    _reviews.value = ReviewsState(reviews = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setCollectionWanted(setId: Int, isWanted: Int) = viewModelScope.launch {
        bricksetUseCases.setCollectionWanted(setId,isWanted)
    }
}

data class ImagesState (
    val isLoading: Boolean = false,
    val images: List<Image> = emptyList(),
    val error: String = ""
)

data class ReviewsState (
    val isLoading: Boolean = false,
    val reviews: List<Review> = emptyList(),
    val error: String = ""
)
