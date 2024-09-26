package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCollectionCount(
    modifier: Modifier = Modifier,
    collectionsCount: String,
    wishlistsCount: String
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = 16.dp,
                horizontal = 8.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            CollectionCountItem(
                isOwned = true,
                setCount = collectionsCount
            )
            CollectionCountItem(
                isOwned = false,
                setCount = wishlistsCount
            )
        }
    }
}