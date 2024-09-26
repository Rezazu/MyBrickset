package com.example.mybrickset.presentation.notes

import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserNotesViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _userNotesState: MutableStateFlow<UserNotesState> = MutableStateFlow(UserNotesState())
    val userNotesState: StateFlow<UserNotesState> get() = _userNotesState

    init {
        getUserNotes()
    }

    fun getUserNotes() {
        bricksetUseCases.getUserNotes().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _userNotesState.value = UserNotesState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _userNotesState.value = UserNotesState(isLoading = true)
                }
                is Resource.Success -> {
                    val notesList = mutableListOf<UserNotesItem>()
                    result.data?.userNotes?.forEach { userNotes ->
                        var set: Set? = null
                        bricksetUseCases.getSetById(userNotes.setID).onEach { result ->
                            when(result) {
                                is Result.Error -> {
                                    _userNotesState.value = UserNotesState(error = result.error)
                                }
                                is Result.Loading -> {
                                    _userNotesState.value = UserNotesState(isLoading = true)

                                }
                                is Result.Success -> {
                                    set = result.data.sets[0]
                                }
                            }
                        }.launchIn(viewModelScope)
                        if (set != null) {
                            notesList.add(UserNotesItem(set = set!!, notes = userNotes.notes))
                        }
                    }
                    _userNotesState.value =
                        UserNotesState(
                            notes = notesList
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class UserNotesState(
    val isLoading: Boolean = false,
    val notes: List<UserNotesItem> = emptyList(),
    val error: String = ""
)

data class UserNotesItem(
    val set: Set,
    val notes: String
)