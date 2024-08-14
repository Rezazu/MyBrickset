package com.example.mybrickset.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun ThemeItem(
    theme: Theme,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .height(100.dp)
            .width(80.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,

        ) {
            Text(
                text = theme.theme,
                style = MaterialTheme.typography.titleSmall
            )
        }
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
            )
        )
    }
}