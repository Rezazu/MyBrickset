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

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Surface(
            color = YellowMain,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ){

        }
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    2.dp,
                    Color.White,
                    CircleShape
                )
                .size(128.dp)
                .align(Alignment.Center),
            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Rez26",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Registered on",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo_brickset_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileCollectionCount(
    modifier: Modifier = Modifier,
    collectionsCount: String,
    wishlistsCount: String
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = 16.dp,
                horizontal = 8.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            CollectionCountItem(
                isOwned = true,
                setCount = collectionsCount
            )
            CollectionCountItem(
                isOwned = false,
                setCount = wishlistsCount
            )
        }
    }
}

@Composable
fun CollectionCountItem(
    modifier: Modifier = Modifier,
    isOwned: Boolean,
    setCount: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isOwned) MatteBlue else MatteRed
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .width(180.dp)
            .height(100.dp)
            .padding(horizontal = 8.dp)
            .border(
                2.dp,
                Color.White,
                RoundedCornerShape(10.dp)
            ),
        onClick = {}
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ){
            Text(
                text = if (isOwned) "Sets Owned" else "On Wishlist",
                style = MaterialTheme.typography.titleMedium,
                color = WhiteBackground,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = setCount,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileMenu(
    modifier: Modifier = Modifier,
    navigateToLegoCollections:() -> Unit,
    navigateToLegoWishlists:() -> Unit,
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
            navigation = {}
        )
    }
}

@Composable
fun ProfileMenuItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    navigation:() -> Unit,
) {
    Column (
        modifier = modifier
            .clickable {
                navigation()
            }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = DarkGray,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
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