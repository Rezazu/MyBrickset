package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.remote.dto.getsets.Set

@Composable
fun SetLazyRow(
    setsList: List<Set>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(setsList.size) {
            setsList[it].let { set ->
                LegoItem(
                    set = set
                )
            }
        }
    }
}