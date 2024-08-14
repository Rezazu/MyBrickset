package com.example.mybrickset.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getthemes.Theme
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

    private val _stateSets = mutableStateOf(SetsState())
    val stateSets: State<SetsState> = _stateSets

    private val _stateThemes = mutableStateOf(ThemeState())
    val stateThemes: State<ThemeState> = _stateThemes

    init {
        getNewReleasedSet()
        getThemes()
    }

    private fun getNewReleasedSet(){
        bricksetUseCases.getNewReleasedSets().onEach { result ->
            when(result) {
                is Result.Success -> {
                    _stateSets.value = SetsState(sets = result.data)
                }
                is Result.Error -> {
                    _stateSets.value = SetsState(error = result.error ?:
                    "An unexpected error, but a welcome one")
                }
                is Result.Loading -> {
                    _stateSets.value = SetsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getThemes() {
        bricksetUseCases.getThemes().onEach { result ->
            when(result) {
                is Result.Success -> {
                    _stateThemes.value = ThemeState(themes = result.data)
                }
                is Result.Error -> {
                    _stateThemes.value = ThemeState(error = result.error ?:
                    "An unexpected error, but a welcome one")
                }
                is Result.Loading -> {
                    _stateThemes.value = ThemeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class SetsState (
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)

data class ThemeState (
    val isLoading: Boolean = false,
    val themes: List<Theme> = emptyList(),
    val error: String = ""
)