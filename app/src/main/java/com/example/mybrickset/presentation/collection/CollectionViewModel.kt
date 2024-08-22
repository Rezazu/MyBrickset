package com.example.mybrickset.presentation.collection

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.domain.usecase.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
):ViewModel() {

    private val _formState = MutableStateFlow(false)
    val formState: StateFlow<Boolean> = _formState

    private fun insertSetCollectionIntoDb(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.insertSetCollection(setCollection)
    }

    fun insertSetCollection(setCollection: SetCollection) {
        insertSetCollectionIntoDb(setCollection)
    }

    fun onFloatingActionButtonClicked(value: Boolean){
        _formState.value = value
    }
}