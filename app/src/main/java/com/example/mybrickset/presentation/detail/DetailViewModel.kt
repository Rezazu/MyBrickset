package com.example.mybrickset.presentation.detail

import android.media.Image
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getadditionalimages.AdditionalImage
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _set: MutableStateFlow<Result<Set>> = MutableStateFlow(Result.Loading)
    val set: StateFlow<Result<Set>> get() = _set

    private val _images = MutableStateFlow(ImagesState())
    val images: StateFlow<ImagesState> = _images

    private fun getAdditionalImages(setId: Int) {
        bricksetUseCases.getAdditionalImage(setId).onEach { result ->
            when(result) {
                is Result.Error -> {
                    _images.value = ImagesState(error = result.error)
                }
                is Result.Loading -> {
                    _images.value = ImagesState(isLoading = true)
                }
                is Result.Success -> {
                    _images.value = ImagesState(images = result.data)
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
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ImagesState (
    val isLoading: Boolean = false,
    val images: List<AdditionalImage> = emptyList(),
    val error: String = ""
)