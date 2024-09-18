package com.example.mybrickset.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun LegoItem(
    set: Set,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier
            .height(260.dp)
            .width(180.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, LightGray),
        onClick = { navigateToDetail() }
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(180.dp)
                        .padding(4.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                    model = ImageRequest
                        .Builder(context)
                        .data(set.image.thumbnailURL)
                        .build(),
                    placeholder = painterResource(id = R.drawable.logo_brickset),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MatteBlue)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = set.theme,
                        style = MaterialTheme.typography.labelSmall,
                        color = White,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.BottomStart)
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = LightGray
            )
            Column(
                modifier
                    .heightIn(min = 72.dp)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = set.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    modifier = Modifier
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = set.number,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LegoItemPreview() {
    MyBricksetTheme {
        LegoItem(
            set = Dummy.DummySet,
            navigateToDetail = {}
        )
    }
}