package com.example.mybrickset.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red

@Composable
fun CollectionFormDeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = {
            Text(
                text = "Delete Set",
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(text = "Are you sure you want to delete the set?")
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(
                    text = "Confirm",
                    color = Red,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Dismiss",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold

                )
            }
        }
    )
}

@Preview
@Composable
private fun CollectionFormDeleteDialogPreview() {
    MyBricksetTheme {
        CollectionFormDeleteDialog(
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}