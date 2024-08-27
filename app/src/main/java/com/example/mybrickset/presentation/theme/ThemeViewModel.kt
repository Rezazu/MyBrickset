package com.example.mybrickset.presentation.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import com.example.mybrickset.presentation.home.Theme1SetsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
    ): ViewModel() {

    private val _themeSets: MutableStateFlow<Result<List<Set>>> = MutableStateFlow(Result.Loading)
    val themeSets: StateFlow<Result<List<Set>>> get() = _themeSets

    fun getSetsByTheme(theme: String){
        bricksetUseCases.getSetsByTheme(theme).onEach { result ->
            when(result) {
                is Result.Error -> {
                }
                is Result.Loading -> {
                    _themeSets.value = Result.Loading
                }
                is Result.Success -> {
                    _themeSets.value = Result.Success(result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
