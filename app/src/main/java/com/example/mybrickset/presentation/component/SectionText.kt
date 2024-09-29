package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
    )
}

@Preview
@Composable
private fun SectionTextPreview() {
    MyBricksetTheme {
        SectionText(title = "Lego Themes")
    }
}