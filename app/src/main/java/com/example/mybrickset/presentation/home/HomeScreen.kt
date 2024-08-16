package com.example.mybrickset.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.AgeRange
import com.example.mybrickset.data.remote.dto.getsets.Barcode
import com.example.mybrickset.data.remote.dto.getsets.CA
import com.example.mybrickset.data.remote.dto.getsets.Collection
import com.example.mybrickset.data.remote.dto.getsets.Collections
import com.example.mybrickset.data.remote.dto.getsets.DE
import com.example.mybrickset.data.remote.dto.getsets.Dimensions
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.UK
import com.example.mybrickset.data.remote.dto.getsets.US
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.component.Banner
import com.example.mybrickset.presentation.component.LegoItem
import com.example.mybrickset.presentation.component.NewsCard
import com.example.mybrickset.presentation.component.SectionText
import com.example.mybrickset.presentation.component.SetLazyRow
import com.example.mybrickset.presentation.component.StoreBanner
import com.example.mybrickset.presentation.component.ThemeItem
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val stateNewSets = viewModel.newStateSets.value
    val stateThemeSets = viewModel.theme1StateSets.value
    val stateThemes = viewModel.stateThemes.value
    HomeContent(
        newSets = stateNewSets.sets,
        themeSets = stateThemeSets.sets,
        themes = stateThemes.themes
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    newSets: List<Set>,
    themeSets: List<Set>,
    themes: List<Theme>,
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
        SectionText(title = "Lego Themes")

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(themes.size) {
                themes[it].let { theme ->
                    ThemeItem(
                        theme = theme
                    )
                }
            }
        }
        SectionText(title = "New 2024 Released sets")
        SetLazyRow(setsList = newSets)
        SectionText(title = "Celebrate Star Wars Day!")
        Banner(banner = R.drawable.starwars_banner, modifier = Modifier.padding(top = 8.dp))
        SetLazyRow(setsList = themeSets, modifier = Modifier.padding(top = 8.dp))
        StoreBanner(
            location = "LEGO® Summarecon Mall Bekasi – 1st Floor",
            image = R.drawable.banner_store,
            modifier = Modifier)
        Banner(banner = R.drawable.brickset_banner)
    }
}



