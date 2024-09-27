package com.example.mybrickset.presentation.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun UserNoteItem(
    set: Set,
    note: String,
    navigateToDetail:() -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 8.dp)
        ) {
            LegoItemSmaller(
                setName = set.name,
                setNumber = "${set.number} - ${set.numberVariant}",
                imageUrl = set.image.imageURL,
            )
//            LegoItemSmall(
//                setName = set.name,
//                setNumber = "${set.number} - ${set.numberVariant}",
//                imageUrl = set.image.imageURL,
//                modifier = Modifier
//                    .align(Alignment.Start)
//            )
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 4,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun LegoItemSmaller(
    setName: String,
    setNumber: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            modifier = modifier
                .padding(4.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    Color.LightGray,
                    RoundedCornerShape(8.dp)
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(R.drawable.img_placeholder)
                .data(Uri.parse(imageUrl))
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,

        )
        Column {
            Text(
                text = setName,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = setNumber,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserNoteItemPreview() {
    MyBricksetTheme {
        UserNoteItem(
            set = Dummy.DummySet,
            note = "This note is not to be read, i repeat this note is not to be read",
            navigateToDetail = {}
        )
    }
}