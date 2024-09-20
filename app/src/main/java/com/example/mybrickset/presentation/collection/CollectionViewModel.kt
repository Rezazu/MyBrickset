package com.example.mybrickset.presentation.collection

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.domain.usecase.BricksetUseCases
import com.example.mybrickset.domain.usecase.local.LocalUseCase
import com.example.mybrickset.presentation.favorite.WantedSetsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val bricksetUseCases: BricksetUseCases
):ViewModel() {

    private val _ownedSets = mutableStateOf(OwnedSetState())
    val ownedSets: State<OwnedSetState> = _ownedSets

    private var _setCollection = mutableListOf<SetCollection>()
    val setCollection: List<SetCollection> = _setCollection

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

    private val _setId = MutableStateFlow("")
    val setId: StateFlow<String> = _setId

    private val _sortState = MutableStateFlow(0)
    val sortState: StateFlow<Int> = _sortState

    init {
        getAllSetCollection()
        getSetsOwned()
    }

    fun getSetsOwned(){
        bricksetUseCases.getSetsOwned().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _ownedSets.value = OwnedSetState(error = result.message ?: "An Error Occured")
                }
                is Resource.Loading -> {
                    _ownedSets.value = OwnedSetState(isLoading = true)
                }
                is Resource.Success -> {
                    _ownedSets.value = OwnedSetState(
                        sets = result.data?.sets?.filterNot {
                            it.name.contains("{?}")
                        } ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllSetCollection() = viewModelScope.launch {
        localUseCase.getAllSetCollection().collect { setCollectionList ->
            _setCollection.clear()
            _setCollection.addAll(setCollectionList)
        }
    }

    fun onSortSelected(filterId: Int) = viewModelScope.launch {
        _sortState.value = filterId
        when(_sortState.value) {
            1 -> {
                localUseCase.getAllSetCollectionOrderByPriceAsc().collect { setCollectionList ->
                    _setCollection.clear()
                    _setCollection.addAll(setCollectionList)
                }
            }
            2 -> {
                localUseCase.getAllSetCollectionOrderByPriceDesc().collect { setCollectionList ->
                    _setCollection.clear()
                    _setCollection.addAll(setCollectionList)
                }
            }
            3 -> {
                localUseCase.getAllSetCollectionOrderByDateAsc().collect { setCollectionList ->
                    _setCollection.clear()
                    _setCollection.addAll(setCollectionList)
                }
            }
            4 -> {
                localUseCase.getAllSetCollectionOrderByDateDesc().collect { setCollectionList ->
                    _setCollection.clear()
                    _setCollection.addAll(setCollectionList)
                }
            }
            else -> {
                getAllSetCollection()
            }
        }
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
        _variantInput.value = "1"
        _imageInput.value = ""
        _nameInput.value = ""
        _conditionInput.value = ""
        _acquiredDateInput.value = ""
        _priceInput.value = ""
    }

    private fun insertEditedSetCollectionIntoDb(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.insertSetCollection(setCollection)
        _numberInput.value = ""
        _variantInput.value = "1"
        _imageInput.value = ""
        _nameInput.value = ""
        _conditionInput.value = ""
        _acquiredDateInput.value = ""
        _priceInput.value = ""
        _setId.value = ""
    }

    fun insertSetCollection(editState: Boolean) {
        if (editState){
            insertEditedSetCollectionIntoDb(
                SetCollection(
                    _numberInput.value,
                    _variantInput.value.toInt(),
                    _imageInput.value,
                    _nameInput.value,
                    _conditionInput.value,
                    _acquiredDateInput.value,
                    _priceInput.value.toDouble(),
                    _setId.value.toInt()
                )
            )
        } else {
            insertSetCollectionIntoDb(
                SetCollection(
                    _numberInput.value,
                    _variantInput.value.toInt(),
                    _imageInput.value,
                    _nameInput.value,
                    _conditionInput.value,
                    _acquiredDateInput.value,
                    _priceInput.value.toDouble(),
                )
            )
        }
    }

    fun deleteSetCollection(setCollection: SetCollection) = viewModelScope.launch {
        localUseCase.deleteSetCollection(setCollection)
    }

    fun onEditSetCollection(setCollection: SetCollection) = viewModelScope.launch {
        _formState.value = true
        setSetId(setCollection.setId!!)

        _numberInput.value = setCollection.number
        _variantInput.value = setCollection.numberVariant.toString()
        _imageInput.value = setCollection.image
        _nameInput.value = setCollection.name
        _conditionInput.value = setCollection.condition
        _acquiredDateInput.value = setCollection.acquiredDate
        _priceInput.value = setCollection.price.toString()
    }

    private fun setSetId(setId: Int) {
        _setId.value = setId.toString()
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

data class OwnedSetState(
    val isLoading: Boolean = false,
    val sets: List<Set> = emptyList(),
    val error: String = ""
)
