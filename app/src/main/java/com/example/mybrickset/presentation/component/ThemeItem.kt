package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.Services
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun ThemeItem(
    theme: Theme,
    navigateToThemeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .height(120.dp)
            .width(100.dp)
            .clickable {
                navigateToThemeScreen()
            },
    ) {
        val imageResource = Services.GetThemeImage(theme = theme.theme)
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun ThemeItemPreview() {
    MyBricksetTheme {
        ThemeItem(
            theme = Theme(
                setCount = 1,
                subthemeCount = 1,
                theme = "Star Wars",
                yearFrom = 1999,
                yearTo = 2024
            ),
            navigateToThemeScreen = {}
        )
    }
}