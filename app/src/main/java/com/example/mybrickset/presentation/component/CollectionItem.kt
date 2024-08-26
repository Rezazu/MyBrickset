package com.example.mybrickset.presentation.component

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.Services
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun CollectionItem(
    setCollection: SetCollection,
    modifier: Modifier = Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(horizontal = 8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.img_placeholder)
                    .data(Uri.parse(setCollection.image))
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 0.dp
                    )
            ) {
                Text(
                    text = setCollection.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "${setCollection.number} - ${setCollection.numberVariant}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Row (
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Acquired Date",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = setCollection.acquiredDate,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = Services.getPriceIDR(setCollection.price),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CollectionItemPreview() {
    MyBricksetTheme {
        CollectionItem(
            setCollection =
                SetCollection(
                    name = "Harry Potter",
                    number = "75123",
                    numberVariant = 1,
                    image = "content://com.android.providers.media.documents/document/image%3A69",
                    condition = "New",
                    acquiredDate = "26/01/2002",
                    price = 300000
                )
        )
    }
}