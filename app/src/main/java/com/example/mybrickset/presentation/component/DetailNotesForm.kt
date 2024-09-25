package com.example.mybrickset.presentation.component

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MatteRed
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.WhiteBackground

@Composable
fun DetailNotesForm(
    modifier: Modifier = Modifier,
    notes: String,
    onNotesInputChanges:(String) -> Unit,
    onNotesSubmitted:() -> Unit,
    onDismissRequest:() -> Unit,
) {

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth(0.9f)
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Add a note",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = {
                        onNotesInputChanges(it)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = WhiteBackground,
                        unfocusedContainerColor = WhiteBackground,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(vertical = 8.dp)
                )
                Button(
                    onClick = {
                        if (notes.isNotEmpty()) {
                            onNotesSubmitted()
                            onDismissRequest()
                            Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()

                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MatteBlue
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(text = "Add Note")
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailNotesFormPreview() {
    MyBricksetTheme {
        DetailNotesForm(
            notes = "Notess",
            onNotesInputChanges = {},
            onNotesSubmitted = {},
            onDismissRequest = {}
        )
    }
}