package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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