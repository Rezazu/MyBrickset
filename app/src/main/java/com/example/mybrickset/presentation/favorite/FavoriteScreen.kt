package com.example.mybrickset.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.LegoItem
import com.example.mybrickset.presentation.component.SectionText
import com.example.mybrickset.presentation.error.ErrorScreen
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val wantedSetsState = viewModel.wantedSets.value

    wantedSetsState.let { setsState ->
        if (setsState.error.isNotBlank()) {
            ErrorScreen(message = setsState.error)
        } else if(setsState.sets.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SectionText(
                    title = "Your Wanted Sets"
                )
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    ),
                ) {
                    val data = setsState.sets
                    items(data.size) {
                        data[it].let { set ->
                            LegoItem(
                                set = set,
                                navigateToDetail = {
                                    navController.navigate(Screen.DetailScreen(setId = set.setID))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    MyBricksetTheme {
//        FavoriteScreen()
    }
}