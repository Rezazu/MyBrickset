package com.example.mybrickset.presentation.component

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.img_placeholder)
                    .data(Uri.parse(setCollection.image))
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column (
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
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${setCollection.number} - ${setCollection.numberVariant}",
                    style = MaterialTheme.typography.bodySmall,
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

@Composable
fun CollectionItem2(
    setCollection: SetCollection,
    modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
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
                Image(
                    painter = painterResource(
                        id = Services.getConditionIcon(setCollection.condition)
                    ),
                    contentDescription = "Condition",
                    modifier = Modifier
                        .size(36.dp)
                )

            }

            HorizontalDivider(modifier = Modifier
                .height(1.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Color.LightGray
                    )
                )
            )
            Row (modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterVertically),
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(R.drawable.img_placeholder)
                        .data(Uri.parse(setCollection.image))
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 0.dp
                        )
                ) {
                    Text(
                        text = setCollection.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${setCollection.number} - ${setCollection.numberVariant}",
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Text(
                        text = Services.getPriceIDR(setCollection.price),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
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
        CollectionItem2(
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