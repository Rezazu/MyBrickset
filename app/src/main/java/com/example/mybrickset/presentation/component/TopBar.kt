package com.example.mybrickset.presentation.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mybrickset.DefaultTopBar
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.SearchWidgetState

@Composable
fun TopBar(
    navController: NavHostController,
    searchWidgetState: SearchWidgetState,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit
) {
    when(searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultTopBar(
                onSearchTriggered = { onSearchTriggered() })
        }
        SearchWidgetState.OPENED -> {
            SearchBar(
                query = query,
                onSearch = onSearch,
                onQueryChange = onQueryChange,
                onCloseClicked = onCloseClicked,
                navigateToSearchScreen = {
                    navController.navigate(Screen.SearchScreen.createRoute(query))
                }
            )
        }
    }
}