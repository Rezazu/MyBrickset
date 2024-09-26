package com.example.mybrickset.presentation.component

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun CollectionHeader(
    setCount: Int,
    sumPrice: Double?,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column (
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("You have a total of ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                        append(setCount.toString())
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append(" item in your collection")
                    }
                },
                modifier = Modifier,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .wrapContentSize()
            ) {
                Text(
                    text = "Total Spent",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray

                )
                Spacer(modifier = Modifier.width(72.dp))

                Text(
                    text = "Rp. ${"%,d".format(sumPrice?.toInt())}",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_id),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .border(
                            1.dp,
                            DarkGray,
                            CircleShape
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColectionHeaderPreview() {
    MyBricksetTheme {
        CollectionHeader(
            setCount = 2,
            sumPrice = 124000.42
        )
    }
}