package com.example.mybrickset.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.data.Result
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.LegoItem
import com.example.mybrickset.presentation.error.ErrorScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    query: String,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController,
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



                    items(data.size) {
                        data[it].let { set ->
                            LegoItem(
                                set = set,
                                navigateToDetail = {
                                    navController.navigate(Screen.DetailScreen(set.setID))
                                },
                                modifier = Modifier.animateItem()
                            )
                        }
                    }
                }
            }

            is Result.Error -> {
                ErrorScreen(message = result.error)
            }
        }
    }
}