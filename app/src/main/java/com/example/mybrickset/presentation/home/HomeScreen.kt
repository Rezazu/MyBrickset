package com.example.mybrickset.presentation.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.Banner
import com.example.mybrickset.presentation.component.NewsCard
import com.example.mybrickset.presentation.component.SectionText
import com.example.mybrickset.presentation.component.SetLazyRow
import com.example.mybrickset.presentation.component.StoreBanner
import com.example.mybrickset.presentation.component.ThemeItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current

    val stateNewSets = viewModel.newStateSets.value
    val stateThemeSets = viewModel.theme1StateSets.value
    val stateThemes = viewModel.stateThemes.value
    Column {
        HomeContent(
            newSets = stateNewSets.sets,
            themeSets = stateThemeSets.sets,
            themes = stateThemes.themes,
            context = context,
            navController = navController
        )
    }
}

@Composable
fun HomeContent(
    newSets: List<Set>,
    themeSets: List<Set>,
    themes: List<Theme>,
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(pageCount = { 5 })

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(state = pagerState) { page ->
            val listDummyNews = Dummy.DummyNews
            NewsCard(
                listDummyNews[page]
            )
        }
        SectionText(title = "LEGO Themes")

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(themes.size) {
                themes[it].let { theme ->
                    ThemeItem(
                        theme = theme,
                        navigateToThemeScreen = { navController.navigate(Screen.ThemeScreen(
                            theme = theme
                        ))
                        }
                    )
                }
            }
        }
        SectionText(title = "New 2024 Released sets")
        SetLazyRow(setsList = newSets, navController = navController)
        SectionText(title = "Celebrate Star Wars Day!")
        Banner(
            banner = R.drawable.starwars_banner,
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                val theme = Theme(
                    setCount = 0,
                    subthemeCount = 0,
                    theme = "Star Wars",
                    yearFrom = 0,
                    yearTo = 0
                )
                navController.navigate(Screen.ThemeScreen(theme))

            }
        )
        SetLazyRow(setsList = themeSets, navController = navController, modifier = Modifier.padding(top = 8.dp))
        StoreBanner(
            location = "LEGO® Summarecon Mall Bekasi – 1st Floor",
            image = R.drawable.banner_store,
            modifier = Modifier
                .padding(vertical = 8.dp))
        Banner(
            banner = R.drawable.brickset_banner,
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://brickset.com/")
                )
                startActivity(context, intent, null)
            }
        )
    }
}



