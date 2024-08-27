package com.example.mybrickset.presentation

import com.example.mybrickset.data.remote.dto.getthemes.Theme
import kotlinx.serialization.Serializable

sealed class Screen(
) {
//    object LoginScreen: Screen("login_screen")
//
//    object HomeScreen: Screen("home_screen")
//    object CollectionScreen: Screen("collection_screen")
//    object ProfileScreen: Screen("profile_screen")
//
//    object SearchScreen: Screen("search_screen/{query}") {
//        fun createRoute(query: String) = "search_screen/$query"
//    }
//    object DetailScreen: Screen("detail_screen")
//
//    object ThemeScreen: Screen("theme_screen/{theme}") {
//        fun createRoute(theme: String) = "theme_screen/$theme"
//    }

    @Serializable
    data object LoginScreen : Screen()

    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object CollectionScreen : Screen()

    @Serializable
    data object ProfileScreen : Screen()

    @Serializable
    data object DetailScreen : Screen()

    @Serializable
    data class SearchScreen(
        val query: String
    ): Screen()

    @Serializable
    data class ThemeScreen(
        val theme: Theme
    ): Screen()
}