package com.example.mybrickset.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun DetailNotes(
    notes: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notes),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Your Note",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = notes,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailNotesPreview() {
    MyBricksetTheme {
        DetailNotes(notes = "Probably Bought in January 2025, if I have the money :) and also a couple of other set")
    }
}