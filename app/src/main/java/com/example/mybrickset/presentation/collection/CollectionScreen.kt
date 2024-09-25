package com.example.mybrickset.presentation.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.BricksetCollectionContent
import com.example.mybrickset.presentation.component.CollectionHeader
import com.example.mybrickset.presentation.component.CollectionItem
import com.example.mybrickset.presentation.component.LegoItem
import com.example.mybrickset.presentation.component.LocalCollectionContent
import com.example.mybrickset.presentation.component.SectionText
import com.example.mybrickset.presentation.error.ErrorScreen
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground
import com.example.mybrickset.presentation.ui.theme.YellowMain
import kotlinx.coroutines.launch
import java.lang.Double

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val formState by viewModel.formState.collectAsState()
    val filterState by viewModel.sortState.collectAsState()

    val ownedSetState = viewModel.ownedSets.value

    val setCollectionList = viewModel.setCollection

    val sumPrice = viewModel.getSumPrice().collectAsState(initial = 0.00)
    val setCount = viewModel.getSetCount().collectAsState(initial = 0)

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 2 }, initialPage = 0)
    val selectedTab = remember {
        derivedStateOf { pagerState.currentPage }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onFloatingActionButtonClicked(true)
                },
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add") }
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Surface(
                modifier = modifier
                    .fillMaxSize()
            ) {
                if (formState) {
                    Dialog(
                        properties =  DialogProperties( usePlatformDefaultWidth = false ),
                        onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) }
                    ) {
                        CollectionForm(
                            onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) },
                        )
                    }
                }
            }
            Column {
                HorizontalDivider()
                TabRow(
                    selectedTabIndex = selectedTab.value,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CollectionTab.entries.forEachIndexed { index, collectionTab ->
                        Tab(
                            selected = selectedTab.value == index,
                            selectedContentColor = DarkGray,
                            unselectedContentColor = Color.LightGray,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(collectionTab.ordinal)
                                }
                            },
                            text = { Text(text = collectionTab.text)}
                        )
                    }
                }
                HorizontalDivider()
                HorizontalPager(state = pagerState) { page ->
                    when(page) {
                        0 -> {
                            LocalCollectionContent(
                                setCount = setCount.value,
                                sumPrice = sumPrice.value,
                                sortId = filterState,
                                setCollectionList = setCollectionList,
                                onDeleteSetCollection = viewModel::deleteSetCollection,
                                onEditSetCollection = viewModel::onEditSetCollection,
                                onFilterSelected = viewModel::onSortSelected,
                            )
                        }
                        1 -> {
                            ownedSetState.let { setsState ->
                                if (setsState.error.isNotBlank()) {
                                    ErrorScreen(message = setsState.error)
                                } else if(setsState.sets.isNotEmpty()) {
                                    BricksetCollectionContent(
                                        sets = setsState.sets,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class CollectionTab(
    val text: String
) {
    Local(
        text = "Local Collection"
    ),
    Brickset(
        text = "Brickset Collection"
    )
}