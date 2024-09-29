package com.example.mybrickset.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberForm(
    numberInput: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = numberInput,
        onValueChange = onValueChange,
        label = { Text(text = label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold) },
        textStyle = TextStyle(fontWeight = FontWeight.SemiBold),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.DarkGray,
        ),
    )
}