package com.example.mybrickset.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
   HomeContent()
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Banner()
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.brickset_banner),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    MyBricksetTheme {
        HomeContent()
    }
}