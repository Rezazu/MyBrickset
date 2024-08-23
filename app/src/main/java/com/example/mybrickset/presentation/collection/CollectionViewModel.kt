package com.example.mybrickset.presentation.collection

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> = _nameInput

    private val _numberInput = MutableStateFlow("")
    val numberInput: StateFlow<String> = _numberInput

    private val _variantInput = MutableStateFlow(0)
    val variantInput: StateFlow<Int> = _variantInput

    private val _imageInput = MutableStateFlow("")
    val imageInput: StateFlow<String> = _imageInput

    private val _conditionInput = MutableStateFlow("")
    val conditionInput: StateFlow<String> = _conditionInput

    private val _acquiredDatenInput = MutableStateFlow("")
    val acquiredDateInput: StateFlow<String> = _acquiredDatenInput

    private val _priceInput = MutableStateFlow(0)
    val priceInput: StateFlow<Int> = _priceInput

    private fun insertSetCollectionIntoDb(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.insertSetCollection(setCollection)
    }

    fun insertSetCollection(setCollection: SetCollection) {
        insertSetCollectionIntoDb(setCollection)
    }

    fun onFloatingActionButtonClicked(value: Boolean){
        _formState.value = value
    }

    fun onNameInputChange(input: String) {
        _nameInput.value = input
    }

    fun onNumberInputChange(input: String) {
        _numberInput.value = input
    }

    fun onVariantInputChange(input: String) {
        _variantInput.value = input.toInt()
    }

    fun onImageInputChange(input: String) {
        _imageInput.value = input
    }

    fun onConditionInputChange(input: String) {
        _conditionInput.value = input
    }

    fun onAcquiredDateInputChange(input: String) {
        _acquiredDatenInput.value = input
    }

    fun onPriceInputChange(input: String) {
        _priceInput.value = input.toInt()
    }
}