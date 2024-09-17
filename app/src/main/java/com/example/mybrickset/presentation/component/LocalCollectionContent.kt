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
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun LocalCollectionContent(
    setCount: Int,
    sumPrice: Double,
    setCollectionList: List<SetCollection>,
    onDeleteSetCollection: (setCollection: SetCollection) -> Unit,
    onEditSetCollection:(setCollection: SetCollection) -> Unit,
    modifier: Modifier = Modifier
) {

    var editState by remember {
        mutableStateOf(false)
    }

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
//                Button(
//                    onClick = {},
//                    shape = RoundedCornerShape(10.dp),
//                    contentPadding = PaddingValues(0.dp),
//                    modifier = Modifier
//                        .width(72.dp)
//                ) {
//                    Text(text = "Filter")
//                }
//                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        editState = !editState
                    },
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .width(80.dp)
                ) {
                    Text(text = "Manage")
                }
            }
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

            )
    }
}