package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.collection.CollectionSort
import com.example.mybrickset.presentation.collection.CollectionViewModel
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun LocalCollectionContent(
    modifier: Modifier = Modifier,
    setCount: Int,
    sumPrice: Double,
    sortId: Int,
    setCollectionList: List<SetCollection>,
    onDeleteSetCollection: (setCollection: SetCollection) -> Unit,
    onEditSetCollection:(setCollection: SetCollection) -> Unit,
    onFilterSelected:(filterId: Int) -> Unit,
) {

    var editState by remember {
        mutableStateOf(false)
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold (

    ) { contentPadding ->

        Surface (
            color = WhiteBackground,
            modifier = modifier
                .fillMaxSize()
        ) {
            Column {
                CollectionHeader(setCount, sumPrice)
                HorizontalDivider()
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                ) {
                    Button(
                        onClick = {
                            showBottomSheet = !showBottomSheet
                        },
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MatteBlue
                        ),
                        modifier = Modifier
                            .width(80.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sort",
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            editState = !editState
                        },
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MatteBlue
                        ),
                        modifier = Modifier
                            .width(80.dp)
                    ) {
                        Text(text = "Manage")
                    }
                }
                Dummy.radioOptions[sortId]?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkGray,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                }
                HorizontalDivider()
                LazyColumn (
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(setCollectionList.size) {
                        CollectionItem(
                            setCollection = setCollectionList[it],
                            editState = editState,
                            onDeleteSetCollection = onDeleteSetCollection,
                            onEditSetCollection = onEditSetCollection
                        )
                    }
                }
            }
        }
        if (showBottomSheet) {
            CollectionSort(
                onFilterSelected = onFilterSelected,
                onDismissRequest = {
                    showBottomSheet = false
                },
                sortId = sortId
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CollectionContentPreview() {
    MyBricksetTheme {
        LocalCollectionContent(
            setCollectionList = Dummy.DummyCollection,
            onDeleteSetCollection = {},
            setCount = 2,
            sumPrice = 599.00,
            onEditSetCollection = {},
            onFilterSelected = {},
            sortId = 3
            )
    }
}