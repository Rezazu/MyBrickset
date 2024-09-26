package com.example.mybrickset.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MatteRed
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun CollectionCountItem(
    modifier: Modifier = Modifier,
    isOwned: Boolean,
    setCount: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isOwned) MatteBlue else MatteRed
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .width(180.dp)
            .height(100.dp)
            .padding(horizontal = 8.dp)
            .border(
                2.dp,
                Color.White,
                RoundedCornerShape(10.dp)
            ),
        onClick = {}
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ){
            Text(
                text = if (isOwned) "Sets Owned" else "On Wishlist",
                style = MaterialTheme.typography.titleMedium,
                color = WhiteBackground,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = setCount,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}