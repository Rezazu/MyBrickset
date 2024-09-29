package com.example.mybrickset.presentation.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.presentation.component.LegoItemSmall
import com.example.mybrickset.presentation.component.ReviewCardVariant
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun ReviewScreen(
    reviews: List<Review>,
    rating: Double,
    reviewCount: Int,
    setName: String,
    setNumber: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        LegoItemSmall(
            setName = setName,
            setNumber = setNumber,
            imageUrl = imageUrl,
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = null,
                tint = YellowMain,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .size(24.dp)
            )
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "${reviewCount} reviews",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        LazyColumn(
            modifier = Modifier
        ) {
            items(reviews.size) { index ->
                ReviewCardVariant(review = reviews[index])
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewScreenPreview() {
    MyBricksetTheme {
        ReviewScreen(
            reviews = listOf(
                Dummy.DummyReview,
                Dummy.DummyReview,
                Dummy.DummyReview,
            ),
            rating = 5.0,
            reviewCount = 5,
            setName = "Mando Starfighter",
            setNumber = "75123-1",
            imageUrl = "https://images.brickset.com/sets/small/75325-1.jpg"
        )
    }
}