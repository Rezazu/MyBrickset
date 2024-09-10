package com.example.mybrickset.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.SearchWidgetState

@Composable
fun TopBar(
    navController: NavHostController,
    searchWidgetState: SearchWidgetState,
    backButtonState: MutableState<Boolean>,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit
) {
    when(searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultTopBar(
                onSearchTriggered = { onSearchTriggered() },
                onBackPressed = { navController.popBackStack() },
                backButtonState = backButtonState,
                navigateToFavoriteScreen = {
                    navController.navigate(Screen.FavoriteScreen)
                }
            )
        }
        SearchWidgetState.OPENED -> {
            SearchBar(
                query = query,
                onSearch = onSearch,
                onQueryChange = onQueryChange,
                onCloseClicked = onCloseClicked,
                navigateToSearchScreen = {
                    navController.navigate(Screen.SearchScreen(
                        query = query
                    ))
                }
            )
        }
    }
}