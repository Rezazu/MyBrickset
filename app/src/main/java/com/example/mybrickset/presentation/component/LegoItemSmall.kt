package com.example.mybrickset.presentation.component

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun LegoItemSmall(
    setName: String,
    setNumber: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentWidth()
            .border(
                1.dp,
                Color.LightGray,
                RoundedCornerShape(12.dp)
            )
            .padding(8.dp)

    ) {
        AsyncImage(
            modifier = Modifier
                .padding(4.dp)
                .size(42.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(R.drawable.img_placeholder)
                .data(Uri.parse(imageUrl))
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = setName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = setNumber,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LegoItemSmallPreview() {
    MyBricksetTheme {
        LegoItemSmall(
            setName = "Mandalorian N-1 Starfighter",
            setNumber = "75123-1",
            imageUrl = "adaw"
        )
    }
}