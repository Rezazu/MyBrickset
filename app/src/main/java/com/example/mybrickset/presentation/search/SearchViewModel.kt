package com.example.mybrickset.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
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
class SearchViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _searchSetsState: MutableStateFlow<Result<List<Set>>> = MutableStateFlow(Result.Loading)

    val searchSetsState: StateFlow<Result<List<Set>>> get() = _searchSetsState

    fun onSearch(query: String) {
        bricksetUseCases.searchSets(query).onEach { result ->
            when(result) {
                is Result.Error -> {
                }
                is Result.Loading -> {
                    _searchSetsState.value = Result.Loading
                }
                is Result.Success -> {
                    _searchSetsState.value = Result.Success(result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

//    fun onSearch(query: String) {
//        bricksetUseCases.searchSets(query).onEach { result ->
//            when(result) {
//                is Result.Success -> {
//                    _searchSetsState.value = SearchSetsState(sets = result.data)
//                }
//                is Result.Error -> {
//                    _searchSetsState.value = SearchSetsState(
//                        error = result.error ?: "An unexpected error, but a welcome one"
//                    )
//                }
//                is Result.Loading -> {
//                    _searchSetsState.value = SearchSetsState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}

data class SearchSetsState (
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)