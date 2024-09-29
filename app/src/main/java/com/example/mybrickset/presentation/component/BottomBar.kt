package com.example.mybrickset.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mybrickset.R
import com.example.mybrickset.presentation.NavigationItem
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.ui.theme.DarkGray

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
        val currentRoute = navBackStackEntry?.destination

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
            if (currentRoute != null) {
                NavigationBarItem(
                    selected = currentRoute.hasRoute(item.screen::class),
                    onClick = {
                        navController.navigate(item.screen) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    label = { Text(text = item.title, fontWeight = FontWeight.SemiBold, color = Color.Black) },
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
}