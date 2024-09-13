package com.example.mybrickset.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _wantedSets = mutableStateOf(WantedSetsState())
    val wantedSets: State<WantedSetsState> = _wantedSets

    init {
        getSetsWanted()
    }

    fun getSetsWanted(){
        bricksetUseCases.getSetsOwned().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _wantedSets.value = WantedSetsState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _wantedSets.value = WantedSetsState(isLoading = true)
                }
                is Resource.Success -> {
                    _wantedSets.value = WantedSetsState(
                        sets = result.data?.sets?.filterNot {
                            it.name.contains("{?}")
                        } ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class WantedSetsState(
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)