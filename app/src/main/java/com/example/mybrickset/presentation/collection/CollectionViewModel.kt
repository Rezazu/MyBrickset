package com.example.mybrickset.presentation.collection

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.domain.usecase.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val pref: AuthPreferences
):ViewModel() {

    private val _formState = MutableStateFlow(false)
    val formState: StateFlow<Boolean> = _formState

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> = _nameInput

    private val _numberInput = MutableStateFlow("")
    val numberInput: StateFlow<String> = _numberInput

    private val _variantInput = MutableStateFlow("1")
    val variantInput: StateFlow<String> = _variantInput

    private val _imageInput = mutableStateOf("")
    val imageInput: State<String> get() = _imageInput

    private val _conditionInput = MutableStateFlow("")
    val conditionInput: StateFlow<String> = _conditionInput

    private val _acquiredDateInput = MutableStateFlow("")
    val acquiredDateInput: StateFlow<String> = _acquiredDateInput

    private val _priceInput = MutableStateFlow("")
    val priceInput: StateFlow<String> = _priceInput

    init {
        getAllSetCollection()
    }

    fun getUserHash(): Flow<String> {
        return pref.getUserHash()
    }


    fun getAllSetCollection(): Flow<List<SetCollection>> {
        return localUseCase.getAllSetCollection()
    }

    fun getSumPrice(): Flow<Double> {
        return localUseCase.getSumPrice()
    }

    fun getSetCount(): Flow<Int> {
        return localUseCase.getSetCount()
    }

    private fun insertSetCollectionIntoDb(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.insertSetCollection(setCollection)
        _numberInput.value = ""
        _variantInput.value = ""
        _imageInput.value = ""
        _nameInput.value = ""
        _conditionInput.value = ""
        _acquiredDateInput.value = ""
        _priceInput.value = ""
    }

    fun insertSetCollection() {
        insertSetCollectionIntoDb(
            SetCollection(
                _numberInput.value,
                _variantInput.value.toInt(),
                _imageInput.value,
                _nameInput.value,
                _conditionInput.value,
                _acquiredDateInput.value,
                _priceInput.value.toDouble()
            )
        )
    }

    fun deleteSetCollection(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.deleteSetCollection(setCollection)
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
        _variantInput.value = input
    }

    fun onImageInputChange(input: String) {
        _imageInput.value = input
    }

    fun onConditionInputChange(input: String) {
        _conditionInput.value = input
    }

    fun onAcquiredDateInputChange(input: String) {
        _acquiredDateInput.value = input
    }

    fun onPriceInputChange(input: String) {
        _priceInput.value = input
    }
}