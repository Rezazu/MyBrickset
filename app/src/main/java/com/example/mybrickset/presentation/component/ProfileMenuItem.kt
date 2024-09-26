package com.example.mybrickset.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.DarkGray

@Composable
fun ProfileMenuItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    navigation:() -> Unit,
) {
    Column (
        modifier = modifier
            .clickable {
                navigation()
            }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = DarkGray,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
}