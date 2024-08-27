package com.example.mybrickset.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.component.LegoItem

@Composable
fun ThemeScreen(
    theme: Theme,
    viewModel: ThemeViewModel = hiltViewModel(),
) {
    Text(text = theme.theme)
    viewModel.themeSets.collectAsState(initial = Result.Loading).value.let { result ->
        when(result) {
            is Result.Error -> {

            }
            is Result.Loading -> {
                viewModel.getSetsByTheme(theme.theme)
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
        }
    }
}