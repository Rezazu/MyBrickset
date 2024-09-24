package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.local.Dummy.radioOptions
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionSort(
    modifier: Modifier = Modifier,
    sortId: Int,
    onFilterSelected:(Int) -> Unit,
    onDismissRequest: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Surface (
            modifier = modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            
            val selectedOption by rememberSaveable {
                mutableStateOf(radioOptions[sortId])
            }

            Column {
                radioOptions.forEach { sort ->
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = sort.value)
                        Spacer(modifier = Modifier.weight(1f))
                        RadioButton(
                            selected = sort.value == selectedOption,
                            onClick = {
                                onFilterSelected(sort.key)
                                onDismissRequest()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionFilterPreview() {
    MyBricksetTheme {
        CollectionSort(
            onFilterSelected = {},
            onDismissRequest = {},
            sortId = 1
        )
    }
}