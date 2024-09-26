package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Surface(
            color = YellowMain,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ){

        }
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    2.dp,
                    Color.White,
                    CircleShape
                )
                .size(128.dp)
                .align(Alignment.Center),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Rez26",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Registered on",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo_brickset_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                )
            }
        }
    }
}