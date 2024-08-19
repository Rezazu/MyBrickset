package com.example.mybrickset.presentation.search

import androidx.lifecycle.ViewModel
import com.example.mybrickset.domain.usecase.BricksetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bricksetUseCases: BricksetUseCases
): ViewModel() {
}