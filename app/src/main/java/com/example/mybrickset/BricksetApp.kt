package com.example.mybrickset

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.AppViewModel
import com.example.mybrickset.presentation.CustomNavType
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.SearchWidgetState
import com.example.mybrickset.presentation.collection.CollectionScreen
import com.example.mybrickset.presentation.component.BottomBar
import com.example.mybrickset.presentation.component.TopBar
import com.example.mybrickset.presentation.detail.DetailScreen
import com.example.mybrickset.presentation.home.HomeScreen
import com.example.mybrickset.presentation.login.LoginScreen
import com.example.mybrickset.presentation.profile.ProfileScreen
import com.example.mybrickset.presentation.review.ReviewScreen
import com.example.mybrickset.presentation.search.SearchScreen
import com.example.mybrickset.presentation.theme.ThemeScreen
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import kotlin.reflect.typeOf

@Composable
fun BricksetApp(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val searchWidgetState by appViewModel.searchWidgetState

    val topBarState = rememberSaveable { mutableStateOf(false) }
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    val backButtonState = rememberSaveable { mutableStateOf(false) }

    val input by appViewModel.query.collectAsState()

    Scaffold (
        topBar = {
            if (topBarState.value == true) {
                TopBar(
                    navController = navController,
                    searchWidgetState = searchWidgetState,
                    backButtonState = backButtonState,
                    query = input,
                    onSearch = appViewModel::onSearch,
                    onQueryChange = appViewModel::onQueryChanged,
                    onCloseClicked = {
                        appViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                        navBackStackEntry?.destination?.let { currentDestionation ->
                            if (currentDestionation.hasRoute(Screen.HomeScreen::class)) {
                                navController.navigate(Screen.HomeScreen)
                            }
                        }
                        navController.navigate(Screen.HomeScreen)
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
            startDestination = Screen.LoginScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.LoginScreen> {
                topBarState.value = false
                bottomBarState.value = false
                LoginScreen(
                    navigateToHomeScreen = {
                        navController.navigate(Screen.HomeScreen) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable<Screen.HomeScreen> {
                topBarState.value = true
                bottomBarState.value = true
                backButtonState.value = false
                HomeScreen(
                    navController = navController,
                )
            }
            composable<Screen.CollectionScreen> {
                topBarState.value = true
                bottomBarState.value = true
                backButtonState.value = false
                CollectionScreen()
            }
            composable<Screen.ProfileScreen> {
                topBarState.value = true
                bottomBarState.value = true
                backButtonState.value = false
                ProfileScreen()
            }
            composable<Screen.DetailScreen> {
                topBarState.value = true
                bottomBarState.value = false
                backButtonState.value = true
                val args = it.toRoute<Screen.DetailScreen>()
                DetailScreen(setId = args.setId, navController = navController)
            }
            composable<Screen.ReviewScreen>(
                typeMap = mapOf(
                    typeOf<List<Review>>() to CustomNavType.review,
                    typeOf<Double>() to CustomNavType.rating
                )
            ) {
                val args = it.toRoute<Screen.ReviewScreen>()
                ReviewScreen(reviews = args.reviews, reviewCount = args.reviewCount, rating = args.rating)
            }
            composable<Screen.SearchScreen> { 
                topBarState.value = true
                bottomBarState.value = false
                backButtonState.value = false
                val args = it.toRoute<Screen.SearchScreen>()
                SearchScreen(query = args.query, navController = navController)
            }
            composable<Screen.ThemeScreen>(
                typeMap = mapOf(
                    typeOf<Theme>() to CustomNavType.theme
                )
            ) {
                topBarState.value = true
                bottomBarState.value = false
                backButtonState.value = true
                val args = it.toRoute<Screen.ThemeScreen>()
                ThemeScreen(theme = args.theme, navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BricksetAppPreview() {
    MyBricksetTheme {

    }
}
