package com.example.mybrickset.presentation.notes

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserNotesViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {

    private val _userNotesState:MutableStateFlow<UserNotesState> = MutableStateFlow(UserNotesState(notes = emptyList()))
    val userNotesState: StateFlow<UserNotesState> get() = _userNotesState.asStateFlow()

    private val _userSetsState:MutableStateFlow<UserSetsState> = MutableStateFlow(UserSetsState(sets = emptyList()))
    val userSetsState: StateFlow<UserSetsState> get() = _userSetsState.asStateFlow()

    private val _images = MutableStateFlow(ImagesState())
    val images: StateFlow<ImagesState> = _images

    private val _listId = mutableListOf<Int>()
//    private val _listId = listOf<Int>(48869,23369)
//    val listId: MutableList<Int> = _listId

    init {
        getUserNotes()
    }

    fun getUserNotes2() {
        _userNotesState.value = UserNotesState(isLoading = true)
        bricksetUseCases.getUserNotes().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _userNotesState.value =
                        UserNotesState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _userNotesState.value = UserNotesState(isLoading = true)
                }
                is Resource.Success -> {
                    val listSet = mutableListOf<Set>()
                    result.data?.userNotes?.forEach { userNotes ->
                        bricksetUseCases.getSetById(userNotes.setID).onEach { result ->
                            when (result) {
                                is Result.Error -> {
                                    _userNotesState.value = UserNotesState(error = result.error)
                                }

                                is Result.Loading -> {
                                    _userNotesState.value = UserNotesState(isLoading = true)

                                }

                                is Result.Success -> {
                                    listSet.add(result.data.sets[0])
                                }
                            }
                        }.launchIn(viewModelScope)
                    }

                }
            }
            _userNotesState.value = UserNotesState(isLoading = false)
        }.launchIn(viewModelScope)
    }

    fun getUserNotes() {
        bricksetUseCases.getUserNotes().onEach { result ->
            when(result) {
                is Resource.Error -> {

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
        val listSet = mutableListOf<Set>()
        _listId.onEach {
            bricksetUseCases.getSetById(it).onEach { result ->
                when(result) {
                    is Result.Error -> {

                    }
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        listSet.add(result.data.sets[0])
                    }
                }
                _userSetsState.value = UserSetsState(sets = listSet)
            }.launchIn(viewModelScope)
        }
    }
}


@Immutable
data class UserNotesState(
    val isLoading: Boolean = false,
    val notes: List<UserNote> = emptyList(),
//    val notes: List<UserNotesItem> = emptyList(),
    val error: String = ""
)

data class UserSetsState(
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
//    val notes: List<UserNotesItem> = emptyList(),
    val error: String = ""
)

@Immutable
data class UserNotesItem(
    val set: Set,
    val notes: String
)