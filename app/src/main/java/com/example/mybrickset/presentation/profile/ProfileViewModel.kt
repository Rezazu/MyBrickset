package com.example.mybrickset.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
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
class ProfileViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
):ViewModel() {

    private val _collectionsCount = mutableStateOf(collectionsCountState())
    val collectionsCount: State<collectionsCountState> get() = _collectionsCount

    private val _wishlistsCount = mutableStateOf(wishlistsCountState())
    val wishlistsCount: State<wishlistsCountState> get() = _wishlistsCount

    init {
        getCountData()
    }

    fun getCountData() {
        bricksetUseCases.getSetsOwned().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _collectionsCount.value = collectionsCountState(error = result.message ?: "An Error Occured")

                }
                is Resource.Loading -> {
                    _collectionsCount.value = collectionsCountState(isLoading = true)
                }
                is Resource.Success -> {
                    _collectionsCount.value = collectionsCountState(collectionsCount = result.data?.matches.toString())
                }
            }
        }.launchIn(viewModelScope)
        bricksetUseCases.getSetsWanted().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _wishlistsCount.value = wishlistsCountState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _wishlistsCount.value = wishlistsCountState(isLoading = true)
                }
                is Resource.Success -> {
                    _wishlistsCount.value = wishlistsCountState(wishlistsCount = result.data?.matches.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class collectionsCountState(
    val isLoading: Boolean = false,
    val collectionsCount: String = "",
    val error: String = ""

)data class wishlistsCountState(
    val isLoading: Boolean = false,
    val wishlistsCount: String = "",
    val error: String = ""
)


