package com.example.mybrickset.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.LegoItem
import com.example.mybrickset.presentation.error.ErrorScreen
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun ThemeScreen(
    theme: Theme,
    navController: NavHostController,
    viewModel: ThemeViewModel = hiltViewModel(),
) {

    val setCount = viewModel.countSets.collectAsState().value
    val themeSetsState = viewModel.themeSets.value

    LaunchedEffect(viewModel) {
        viewModel.getSetsByTheme(theme.theme)
    }

    Surface(
        color = WhiteBackground,
        modifier = Modifier
            .fillMaxSize()
    ) {
        themeSetsState.let { setsState ->
            if (setsState.error.isNotBlank()) {
                ErrorScreen(message = setsState.error)
            } else if (setsState.sets.isNotEmpty()) {
                Column {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Showing ")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(setCount)
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append(" Result")
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp)
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
}

