package com.example.mybrickset.presentation

sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object CollectionScreen: Screen("collection_screen")
    object ProfileScreen: Screen("profile_screen")
}