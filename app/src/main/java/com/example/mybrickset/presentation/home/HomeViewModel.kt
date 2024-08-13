package com.example.mybrickset.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.BricksetRepository
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
    private val bricksetRepository: BricksetRepository
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getNewReleasedSet()
    }

    private fun getNewReleasedSet(){
        bricksetUseCases.getNewReleasedSets().onEach { result ->
            when(result) {
                is Result.Success -> {
                    _state.value = HomeState(sets = result.data)
                }
                is Result.Error -> {
                    _state.value = HomeState(error = result.error ?:
                    "An unexpected error, but a welcome one")
                }
                is Result.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class HomeState (
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)