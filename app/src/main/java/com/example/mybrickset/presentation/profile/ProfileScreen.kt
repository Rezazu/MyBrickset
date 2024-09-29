package com.example.mybrickset.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.ProfileCollectionCount
import com.example.mybrickset.presentation.component.ProfileHeader
import com.example.mybrickset.presentation.component.ProfileMenu
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val collectionsCount = viewModel.collectionsCount.value
    val wishlistsCount = viewModel.wishlistsCount.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader()
        ProfileCollectionCount(
            collectionsCount = collectionsCount.collectionsCount,
            wishlistsCount = wishlistsCount.wishlistsCount
        )
        HorizontalDivider()
        ProfileMenu(
            navigateToLegoCollections = {
                navController.navigate(Screen.CollectionScreen)
            },
            navigateToLegoWishlists = {
                navController.navigate(Screen.FavoriteScreen)
            },
            navigateToUserNotes = {
                navController.navigate(Screen.UserNotesScreen)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview(
) {
    MyBricksetTheme {
        ProfileScreen(
            navController = rememberNavController()
        )
    }
}