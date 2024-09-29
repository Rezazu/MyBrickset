package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mybrickset.data.local.Dummy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionDropDown(
    condition: String,
    onValueChanged: (String) -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .height(72.dp)
            .width(160.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded},
            modifier = Modifier
        ) {
            OutlinedTextField(
                value = condition,
                onValueChange = onValueChanged,
                label = { Text(text = "Condition", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold) },
                textStyle = TextStyle(fontWeight = FontWeight.SemiBold),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.DarkGray,
                ),
                modifier = Modifier
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Dummy.DummyCondition.forEach { conditionItem ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = conditionItem,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            onValueChanged(conditionItem)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}