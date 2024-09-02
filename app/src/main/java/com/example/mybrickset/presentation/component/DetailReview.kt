package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun DetailReview(
    rating: Double,
    reviews: List<Review>,
    reviewCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        if (reviews.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp,
                        bottom = 0.dp,
                        start = 12.dp,
                        end = 12.dp
                    )

            ) {
                Text(
                    text = "Review",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = YellowMain,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                    )
                    Text(
                        text = rating.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${reviewCount} reviews",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Column {
                ReviewCard(review = reviews[0])
                HorizontalDivider()
                ReviewCard(review = reviews[1])
                HorizontalDivider()
            }
        } else {
            Text(
                text = "This set has no review yet",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(vertical = 6.dp)
            )
        }
    }
}