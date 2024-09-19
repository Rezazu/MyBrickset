package com.example.mybrickset.presentation.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.Dummy.radioOptions
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionFilter(
    modifier: Modifier = Modifier,
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
            
            var selectedOption by remember {
                mutableStateOf(radioOptions[1])
            }
            
            Column {
                radioOptions.forEach { filter ->
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = filter.value)
                        Spacer(modifier = Modifier.weight(1f))
                        RadioButton(
                            selected = filter.value == selectedOption,
                            onClick = {
                                onFilterSelected(filter.key)
                                onDismissRequest()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionFilterPreview() {
    MyBricksetTheme {
        CollectionFilter(
            onFilterSelected = {},
            onDismissRequest = {}
        )
    }
}