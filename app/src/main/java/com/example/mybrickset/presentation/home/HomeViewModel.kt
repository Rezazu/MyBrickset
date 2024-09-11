package com.example.mybrickset.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.domain.BricksetRepository
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
): ViewModel() {

    private val _newStateSets = mutableStateOf(NewSetsState())
    val newStateSets: State<NewSetsState> = _newStateSets

    private val _theme1StateSets = mutableStateOf(Theme1SetsState())
    val theme1StateSets: State<Theme1SetsState> = _theme1StateSets

    private val _stateThemes = mutableStateOf(ThemeState())
    val stateThemes: State<ThemeState> = _stateThemes

    init {
        getNewReleasedSet()
        getSetsByTheme("Star Wars")
        if (_stateThemes.value.themes.isEmpty()) {
            getThemes()
        }
    }

    private fun getNewReleasedSet(){
        bricksetUseCases.getNewReleasedSets().onEach { result ->
            when(result) {
                is Result.Success -> {
                    _newStateSets.value = NewSetsState(sets = result.data)
                }
                is Result.Error -> {
                    _newStateSets.value = NewSetsState(error = result.error ?:
                    "An unexpected error, but a welcome one")
                }
                is Result.Loading -> {
                    _newStateSets.value = NewSetsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSetsByTheme(theme: String){
        bricksetUseCases.getSetsByTheme(theme, 30).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _theme1StateSets.value = Theme1SetsState(
                        sets = result.data?.sets?.filterNot {
                            it.name.contains("{?}")
                        } ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _theme1StateSets.value = Theme1SetsState(error = result.message ?:
                    "An unexpected error, but a welcome one")
                }
                is Resource.Loading -> {
                    _theme1StateSets.value = Theme1SetsState(isLoading = true)
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

    private val _query = MutableStateFlow("")
    val query : StateFlow<String> get() = _query
}

data class NewSetsState (
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)

data class Theme1SetsState (
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)

data class ThemeState (
    val isLoading: Boolean = false,
    val themes: List<Theme> = emptyList(),
    val error: String = ""
)
