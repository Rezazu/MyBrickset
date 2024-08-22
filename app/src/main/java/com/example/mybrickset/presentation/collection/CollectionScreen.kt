package com.example.mybrickset.presentation.collection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel()
) {

    val formState by viewModel.formState.collectAsState()

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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (formState) {
                Dialog(
                    onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) }
                ) {
                    CollectionForm(
                        onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) }
                    )
                }
            }
        }

    }
}

@Composable
fun FloatingActionButton(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun CollectionScreenPreview(

) {
    MyBricksetTheme {
        CollectionScreen()
    }

}