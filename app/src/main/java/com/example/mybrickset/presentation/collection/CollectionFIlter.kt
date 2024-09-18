package com.example.mybrickset.presentation.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun CollectionFilter(
    modifier: Modifier = Modifier,
    onPriceAscClicked:() -> Unit,
    onPriceDescClicked:() -> Unit,
    onDateAscClicked:() -> Unit,
    onDateDescClicked:() -> Unit,
    onDismissRequest: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .height(170.dp)
            .width(128.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row {
                Text(
                    text = "Prices Low to High",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 8.sp
                )
                RadioButton(
                    selected = true,
                    onClick = { /*TODO*/ },
                )
            }
        }
    }
}

@Preview
@Composable
private fun CollectionFilterPreview() {
    MyBricksetTheme {
        CollectionFilter(
            onPriceAscClicked = { },
            onPriceDescClicked = { },
            onDateAscClicked = {  },
            onDateDescClicked = {  }
        ) {

        }
    }
}