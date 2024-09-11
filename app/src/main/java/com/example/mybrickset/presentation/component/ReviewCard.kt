package com.example.mybrickset.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp)
    ) {
        Text(
            text = review.author,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = review.rating.overall.toString()
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = null,
                tint = YellowMain,
                modifier = Modifier
                    .size(18.dp)
            )
        }
        Text(
            text = review.title,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = HtmlCompat.fromHtml(review.review, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(4.dp)
        )
    }
}

@Composable
fun ReviewCardVariant(
    review: Review,
    modifier: Modifier = Modifier
) {

    var reviewState by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Column (
            modifier = if (reviewState == false) {
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(
                        vertical = 6.dp,
                    )
            } else {
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(
                        vertical = 6.dp,
                    )
            }

        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = review.author,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector =
                    if (reviewState == false) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            reviewState = !reviewState
                        }
                )
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = review.rating.overall.toString(),
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = null,
                    tint = YellowMain,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
            Text(
                text = review.title,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = HtmlCompat.fromHtml(review.review, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            )
            Icon(
                imageVector =
                if (reviewState == false) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        reviewState = !reviewState
                    }
                    .align(Alignment.End)
                    .padding(
                        vertical = 4.dp,
                        horizontal = 8.dp
                    )
            )
        }
    }
}

@Preview
@Composable
private fun ReviewCardPreview() {
    MyBricksetTheme {
        ReviewCardVariant(
            review = Dummy.DummyReview,
        )
    }
}