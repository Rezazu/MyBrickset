package com.example.mybrickset.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.remote.dto.getsets.Set

@Composable
fun SetLazyRow(
    setsList: List<Set>,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(setsList.size) {
            LegoItem(
                set = setsList[it],
                modifier = Modifier
                    .clickable {
                        navigateToDetail()
                    }
            )
        }
    }
}