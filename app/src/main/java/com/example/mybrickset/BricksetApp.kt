package com.example.mybrickset

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.example.mybrickset.presentation.component.SearchBar
import com.example.mybrickset.presentation.detail.DetailScreen
import com.example.mybrickset.presentation.home.HomeScreen
import com.example.mybrickset.presentation.profile.ProfileScreen
import com.example.mybrickset.presentation.search.SearchScreen
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun BricksetApp(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val searchWidgetState by appViewModel.searchWidgetState

    val query by appViewModel.query.collectAsState()


    Scaffold (
        topBar = {
            TopBar(
                searchWidgetState = searchWidgetState,
                query = query,
                onSearch = appViewModel::onSearch,
                onQueryChange = appViewModel::onQueryChanged,
                onCloseClicked = {
                    appViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                },
                onSearchTriggered = {
                    appViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                }
            )
        },
        bottomBar = {
            if (currentRoute != Screen.DetailScreen.route)
            BottomBar(navController = navController)
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailScreen.route)
                    }
                )
            }
            composable(Screen.CollectionScreen.route) {
                CollectionScreen()
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen()
            }
            composable(Screen.DetailScreen.route) {
                DetailScreen()
            }
            composable(
                Screen.SearchScreen.route,
//                listOf(navArgument("query") { type = NavType.StringType})
            ) {
                val query = it.arguments?.getString("query") ?: "Lego"
            }
        }
    }
}

@Composable
fun TopBar(
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
                onCloseClicked = onCloseClicked)
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

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(R.string.menu_collection),
                icon = Icons.AutoMirrored.Filled.List,
                screen = Screen.CollectionScreen
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.ProfileScreen
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                label = { Text(text = item.title)},
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BricksetAppPreview() {
    MyBricksetTheme {

    }
}
