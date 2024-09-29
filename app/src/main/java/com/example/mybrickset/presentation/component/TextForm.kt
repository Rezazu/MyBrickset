package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextForm(
    textInput: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    fun validate(text: String) {
        isError = text.isNotEmpty()
    }

    OutlinedTextField(
        modifier = modifier,
        value = textInput,
        onValueChange = onValueChange,
        label = { Text(text = label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold) },
        textStyle = TextStyle(fontWeight = FontWeight.SemiBold),
        singleLine = true,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$label cannot be empty",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.DarkGray),
        )
}