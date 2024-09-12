package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.TextOrange

@Composable
fun StoreBanner (
    location: String,
    image: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(160.dp)
            .fillMaxWidth()
            .background(CreamBackground)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.banner_open_store),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.banner_all_store),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = TextOrange,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = modifier
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewStoreBanner() {
    MyBricksetTheme {
        StoreBanner(
            location = "LEGO® Summarecon Mall Bekasi – 1st Floor",
            image = R.drawable.banner_store
        )
    }
}