package com.example.mybrickset.presentation.profile

import android.graphics.drawable.Icon
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mybrickset.R
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.ProfileCollectionCount
import com.example.mybrickset.presentation.component.ProfileHeader
import com.example.mybrickset.presentation.component.ProfileMenu
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MatteRed
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground
import com.example.mybrickset.presentation.ui.theme.YellowMain

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