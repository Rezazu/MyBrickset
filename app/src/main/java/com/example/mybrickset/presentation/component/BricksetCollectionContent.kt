package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun BricksetCollectionContent(
    sets: List<Set>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        color = WhiteBackground,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                SectionText(
                    title = "Your collection listed on"
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_brickset_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(98.dp)
                )
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val data = sets
                items(data.size) {
                    data[it].let { set ->
                        LegoItem(
                            set = set,
                            navigateToDetail = {
                                navController.navigate(Screen.DetailScreen(setId = set.setID))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionContentPreview() {
    MyBricksetTheme {
//        LocalCollectionContent(
//            setCollectionList = Dummy.DummyCollection,
//            onDeleteSetCollection = {},
//            setCount = 2,
//            sumPrice = 599.00,
//            onEditSetCollection = {},
//
//        )
        BricksetCollectionContent(
            sets = listOf(Dummy.DummySet),
            navController = NavHostController(LocalContext.current)
        )
    }
}