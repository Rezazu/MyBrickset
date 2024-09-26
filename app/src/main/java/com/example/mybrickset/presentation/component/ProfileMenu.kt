package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileMenu(
    modifier: Modifier = Modifier,
    navigateToLegoCollections:() -> Unit,
    navigateToLegoWishlists:() -> Unit,
    navigateToUserNotes:() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ProfileMenuItem(
            icon = Icons.Default.Menu,
            title = "Lego Collections",
            navigation = navigateToLegoCollections
        )
        ProfileMenuItem(
            icon = Icons.Default.Favorite,
            title = "Wishlists",
            navigation = navigateToLegoWishlists
        )
        ProfileMenuItem(
            icon = Icons.Default.Edit,
            title = "Your notes",
            navigation = navigateToUserNotes
        )
        ProfileMenuItem(
            icon = Icons.AutoMirrored.Default.ExitToApp,
            title = "Log out",
            navigation = {}
        )
    }
}