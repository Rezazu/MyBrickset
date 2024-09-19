package com.example.mybrickset.presentation.component

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun CollectionHeader(
    setCount: Int,
    sumPrice: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column (
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
        ) {
            Text(text = "You have a total of $setCount item in your collection")
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                verticalAlignment = Alignment.Bottom,
                modifier = modifier
                    .wrapContentSize()
            ) {
                Text(
                    text = "Total Spent",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(72.dp))
                Text(
                    text = "Rp. ${sumPrice} IDR",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColectionHeaderPreview() {
    MyBricksetTheme {
        CollectionHeader(
            setCount = 2,
            sumPrice = 124000.42
        )
    }
}