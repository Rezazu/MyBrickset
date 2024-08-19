package com.example.mybrickset.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    query: String,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToDetail: () -> Unit
) {

}