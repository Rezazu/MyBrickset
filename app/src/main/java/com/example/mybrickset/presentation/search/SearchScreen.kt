package com.example.mybrickset.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.data.Result
import com.example.mybrickset.presentation.component.LegoItem

@Composable
fun SearchScreen(
    query: String,
    viewModel: SearchViewModel = hiltViewModel(),
//    navigateBack: () -> Unit,
//    navigateToDetail: () -> Unit
) {

    viewModel.searchSetsState.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.onSearch(query)
            }
            is Result.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(vertical = 24.dp, horizontal = 8.dp)
                ) {
                    val data = result.data
                    items(data.size) { set ->
                        data[set].let {
                            LegoItem(set = it)
                        }
                    }
                }
            }

            is Result.Error -> {

            }
        }
    }
}