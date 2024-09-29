package com.example.mybrickset.presentation.notes

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.async
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.core.app.NotificationCompat.StreamType
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getusernotes.UserNote
import com.example.mybrickset.presentation.detail.ImagesState
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserNotesViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _userNotesState: MutableStateFlow<UserNotesState> =
        MutableStateFlow(UserNotesState(notes = emptyList()))
    val userNotesState: StateFlow<UserNotesState> get() = _userNotesState.asStateFlow()

    private val _userSetsState: MutableStateFlow<UserSetsState> =
        MutableStateFlow(UserSetsState(sets = emptyList()))
    val userSetsState: StateFlow<UserSetsState> get() = _userSetsState.asStateFlow()

    private val _listId = mutableListOf<Int>()
    private val _listSets = mutableListOf<Set>()

    init {
        getUserNotes()
    }

    fun getUserNotes() {
        bricksetUseCases.getUserNotes().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _userNotesState.value = UserNotesState(error = result.message ?: "Something happened")
                }

                is Resource.Loading -> {
                    _userNotesState.value = UserNotesState(isLoading = true)
                }

                is Resource.Success -> {
                    _userNotesState.value = UserNotesState(notes = result.data!!.userNotes)
                    result.data.userNotes.forEach {
                        _listId += it.setID
                    }
                    getSetById()
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getSetById() {
        viewModelScope.launch {
            val completedCalls = MutableStateFlow(0)
            _listId.forEach { setID ->
                launch(Dispatchers.IO) {
                    bricksetUseCases.getSetById(setID).onEach{ result ->
                        when(result) {
                            is Result.Error -> {

                            }
                            is Result.Loading -> {

                            }
                            is Result.Success -> {
                                _listSets.add(result.data.sets[0])
                                completedCalls.value++
                            }
                        }
                    }.launchIn(viewModelScope) // Launch within the current coroutine
                }
            }

            completedCalls.collect { count ->
                if (count == _listId.size) {
                    _userSetsState.value = UserSetsState(sets = _listSets)
                }
            }
        }
    }
}


@Immutable
data class UserNotesState(
    val isLoading: Boolean = false,
    val notes: List<UserNote> = emptyList(),
    val error: String = ""
)

data class UserSetsState(
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)