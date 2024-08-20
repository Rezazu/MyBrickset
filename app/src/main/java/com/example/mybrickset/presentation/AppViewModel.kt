package com.example.mybrickset.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _searhWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searhWidgetState

    private val _query = MutableStateFlow("")
    val query : StateFlow<String> get() = _query

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searhWidgetState.value = newValue
    }

    fun onQueryChanged(query: String) {
        _query.value = query
    }
    fun onSearch(query: String) {

    }

}

enum class SearchWidgetState{
    OPENED,
    CLOSED
}