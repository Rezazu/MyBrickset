package com.example.mybrickset.presentation.theme

import android.content.res.Resources.Theme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
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

    private val _themeSets: MutableStateFlow<Resource<List<Set>>> = MutableStateFlow(Resource.Loading())
    val themeSets: StateFlow<Resource<List<Set>>> = _themeSets

    private val _themeSets2 = mutableStateOf(ThemeSetsState())
    val themeSets2: State<ThemeSetsState> = _themeSets2

    private val _countSets: MutableStateFlow<String> = MutableStateFlow("")
    val countSets: StateFlow<String> get() = _countSets

    fun getSetsByTheme(theme: String){
        bricksetUseCases.getSetsByTheme(theme, 0).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _themeSets.value = Resource.Error(result.message  ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _themeSets.value = Resource.Loading()
                }
                is Resource.Success -> {
                    _themeSets.value = Resource.Success(
                        result.data?.sets?.filterNot {
                            it.name.contains("{?}")
                        } ?: emptyList()
                    )
                    _countSets.value = result.data?.matches.toString()
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getSetsByTheme2(theme: String){
        bricksetUseCases.getSetsByTheme(theme, 0).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _themeSets2.value = ThemeSetsState(error = result.message  ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _themeSets2.value = ThemeSetsState(isLoading = true)
                }
                is Resource.Success -> {
                    _themeSets2.value = ThemeSetsState(
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
