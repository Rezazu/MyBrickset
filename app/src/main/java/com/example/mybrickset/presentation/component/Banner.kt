package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme


@Composable
fun Banner(
    banner: Int,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(id = banner),
            contentDescription = null,
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun BannerPreview() {
    MyBricksetTheme {
        Banner(
            banner = R.drawable.starwars_banner,
            onClick = {}
        )
    }
}