package com.example.mybrickset.presentation.theme

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
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
    ): ViewModel() {

    private val _themeSets = mutableStateOf(ThemeSetsState())
    val themeSets: State<ThemeSetsState> = _themeSets

    private val _countSets: MutableStateFlow<String> = MutableStateFlow("")
    val countSets: StateFlow<String> get() = _countSets

    fun getSetsByTheme(theme: String){
        bricksetUseCases.getSetsByTheme(theme, 0).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _themeSets.value = ThemeSetsState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _themeSets.value = ThemeSetsState(isLoading = true)
                }
                is Resource.Success -> {
                    _themeSets.value = ThemeSetsState(
                        sets = result.data?.sets?.filterNot {
                            it.name.contains("{?}")
                        } ?: emptyList()
                    )
                    _countSets.value = result.data?.matches.toString()
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ThemeSetsState(
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)
