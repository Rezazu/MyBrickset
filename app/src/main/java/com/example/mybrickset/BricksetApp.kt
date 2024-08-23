package com.example.mybrickset

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mybrickset.presentation.AppViewModel
import com.example.mybrickset.presentation.NavigationItem
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.SearchWidgetState
import com.example.mybrickset.presentation.collection.CollectionScreen
import com.example.mybrickset.presentation.component.BottomBar
import com.example.mybrickset.presentation.component.SearchBar
import com.example.mybrickset.presentation.component.TopBar
import com.example.mybrickset.presentation.detail.DetailScreen
import com.example.mybrickset.presentation.home.HomeScreen
import com.example.mybrickset.presentation.login.LoginScreen
import com.example.mybrickset.presentation.profile.ProfileScreen
import com.example.mybrickset.presentation.search.SearchScreen
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun BricksetApp(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val searchWidgetState by appViewModel.searchWidgetState

    val topBarState = rememberSaveable { mutableStateOf(false) }
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    val input by appViewModel.query.collectAsState()

    Scaffold (
        topBar = {
            if (topBarState.value == true) {
                TopBar(
                    navController = navController,
                    searchWidgetState = searchWidgetState,
                    query = input,
                    onSearch = appViewModel::onSearch,
                    onQueryChange = appViewModel::onQueryChanged,
                    onCloseClicked = {
                        appViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                        if (currentRoute != Screen.HomeScreen.route) {
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    },
                    onSearchTriggered = {
                        appViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                    }
                )
            }
        },
        bottomBar = {
            if (bottomBarState.value == true) {
                BottomBar(navController = navController)
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.LoginScreen.route) {
                topBarState.value = false
                bottomBarState.value = false
                LoginScreen(
                    navigateToHomeScreen = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable(Screen.HomeScreen.route) {
                topBarState.value = true
                bottomBarState.value = true
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailScreen.route)
                    }
                )
            }
            composable(Screen.CollectionScreen.route) {
                topBarState.value = true
                bottomBarState.value = true
                CollectionScreen()
            }
            composable(Screen.ProfileScreen.route) {
                topBarState.value = true
                bottomBarState.value = true
                ProfileScreen()
            }
            composable(Screen.DetailScreen.route) {
                bottomBarState.value = false
                DetailScreen()
            }
            composable(
                Screen.SearchScreen.route,
                listOf(navArgument("query") { type = NavType.StringType})
            ) {
                val query = it.arguments?.getString("query") ?: "Lego"
                bottomBarState.value = false
                SearchScreen(query)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    onSearchTriggered: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(text = "")
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_lego),
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(36.dp)
                    .padding(start = 8.dp)
            )

        },
        actions = {
            IconButton(
                onClick = { onSearchTriggered() },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun BricksetAppPreview() {
    MyBricksetTheme {

    }
}
