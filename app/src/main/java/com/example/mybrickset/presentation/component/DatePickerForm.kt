package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerForm(
    date: String,
    onValueChange:(String) -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    var selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box {
        OutlinedTextField(
            value = date,
            onValueChange = { },
            label = { Text(text = "Acquired Date", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold) },
            textStyle = TextStyle(fontWeight = FontWeight.SemiBold),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.DarkGray,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
                ),
            readOnly = true,
            trailingIcon =
            {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        onValueChange(selectedDate)
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                }) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}