package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun DetailUserReview(
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    rating: Int,
    isRated: Boolean,
    onRatingChanged: (Int) -> Unit
) {

    val density = LocalDensity.current.density
    val starSize = (16f * density).dp
    val starSpacing = (0.5f * density).dp

    var rate by remember {
        mutableStateOf(rating)
    }

    Box (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Your Rating",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                for (i in 1..maxStars) {
                    val isSelected = i <= rating
                    val iconTintColor = if (isSelected) YellowMain else Color.LightGray
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_filled),
                        contentDescription = null,
                        tint = iconTintColor,
                        modifier = Modifier
                            .selectable(
                                selected = isSelected,
                                onClick = {
                                    rate = i
                                }
                            )
                            .width(starSize)
                            .height(starSize)
                    )

                    if (i < maxStars) {
                        Spacer(modifier = Modifier.width(starSpacing))
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            if(rating > 0) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(
                        text = if (isRated) "Change Rating" else "Add Rating"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailUserReviewPreview() {
    MyBricksetTheme {
        DetailUserReview(
            maxStars = 5,
            rating = 3,
            isRated = false,
            onRatingChanged = {}

        )
    }
}