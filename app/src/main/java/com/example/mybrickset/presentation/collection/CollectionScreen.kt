package com.example.mybrickset.presentation.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.component.CollectionItem
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val formState by viewModel.formState.collectAsState()
    val setCollectionList = viewModel.getAllSetCollection().collectAsState(initial = emptyList())
    val sumPrice = viewModel.getSumPrice().collectAsState(initial = 0.00)
//    val sumPrice = 124000.00
    val setCount = viewModel.getSetCount().collectAsState(initial = 0)

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
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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
            CollectionContent(
                setCount = setCount.value,
                sumPrice = sumPrice.value,
                setCollectionList = setCollectionList.value,
                onDeleteSetCollection = viewModel::deleteSetCollection
            )
        }

    }
}

@Composable
fun CollectionContent(
    setCount: Int,
    sumPrice: Double,
    setCollectionList: List<SetCollection>,
    onDeleteSetCollection: (setCollection: SetCollection) -> Unit,
    modifier: Modifier = Modifier
) {

    var editState by remember {
        mutableStateOf(false)
    }

    Surface (
        color = WhiteBackground,
    ) {
        Column {
            CollectionHeader(setCount, sumPrice)
            HorizontalDivider()
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .width(72.dp)
                ) {
                    Text(text = "Filter")
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        editState = !editState
                    },
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .width(72.dp)
                ) {
                    Text(text = "Edit")
                }
            }
            LazyColumn (
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(setCollectionList.size) {
                    CollectionItem(
                        setCollection = setCollectionList[it],
                        editState = editState,
                        onDeleteSetCollection = onDeleteSetCollection
                    )
                }
            }
        }
    }
}

@Composable
fun CollectionHeader(
    setCount: Int,
    sumPrice: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 4.dp,
                    vertical = 8.dp
                )
        ) {
            CollectionTextRow(
                label = "Total Collection",
                value = setCount.toString()
            )
            Row (
                verticalAlignment = Alignment.Bottom,
                modifier = modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Total Spent",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = sumPrice.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun CollectionTextRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row (modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .fillMaxWidth(0.4f)
        )
        Text(
            text = ": ",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            color = Color.DarkGray
        )
    }
}



@Preview (showBackground = true)
@Composable
private fun CollectionContentPreview() {
    MyBricksetTheme {
        CollectionContent(
            setCollectionList = Dummy.DummyCollection,
            onDeleteSetCollection = {},
            setCount = 2,
            sumPrice = 599.00
        )
//        CollectionHeader()
    }

}