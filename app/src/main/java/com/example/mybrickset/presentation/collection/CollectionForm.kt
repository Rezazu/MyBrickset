package com.example.mybrickset.presentation.collection

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.component.ConditionDropDown
import com.example.mybrickset.presentation.component.DatePickerForm
import com.example.mybrickset.presentation.component.TextForm
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionForm(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit,
) {

    val context = LocalContext.current
    val nameInput = viewModel.nameInput.collectAsState()
    val numberInput = viewModel.numberInput.collectAsState()
    val conditionInput = viewModel.conditionInput.collectAsState()
    val dateInput = viewModel.acquiredDateInput.collectAsState()


    var nameText by remember { mutableStateOf("") }

    var numberText by remember { mutableStateOf("") }
    var variantText by remember { mutableStateOf("") }

    var uriImage by remember { mutableStateOf<Uri?>(null) }
    var conditionText by remember { mutableStateOf("") }

    var priceText by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let { uriImage = it }
        }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth(0.9f)
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .align(Alignment.Center) // or to a specific child
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                Text(
                    text = "Add Set to Your Collection",
                    style = MaterialTheme.typography.titleMedium
                )
                TextForm(
                    textInput = nameInput.value,
                    label = "Name of The Set",
                    onValueChange = viewModel::onNameInputChange,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(
                        textInput = numberInput.value,
                        label = "Set Number",
                        onValueChange = viewModel::onNumberInputChange,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    OutlinedTextField(
                        value = variantText,
                        onValueChange = { variantText = it },
                        label = { Text(text = "",style = MaterialTheme.typography.labelSmall) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.DarkGray,
                            cursorColor = Color.Black,
                        ),
                        )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (uriImage != null) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(150.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .placeholder(R.drawable.img_placeholder)
                                .data(uriImage)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    ConditionDropDown(
                        condition = conditionText,
                        onValueChanged = viewModel::onConditionInputChange,
                    )
                }
                Button(
                    onClick = {
                                launcher.launch("image/*")
                              },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Select Image")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DatePickerForm(
                        date = dateInput.value,
                        onValueChange =  viewModel::onAcquiredDateInputChange
                    )
                    HorizontalDivider(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = priceText,
                        onValueChange = { priceText = it },
                        label = { Text(text = "Price",style = MaterialTheme.typography.labelSmall) },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.DarkGray,
                            cursorColor = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Button(onClick = { onDismissRequest() }) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    uriImage?.let {
                        saveImageToInternalStorage(context,
                            it
                        )
                        onDismissRequest()
                    }
                }) {
                    Text(text = "Add Set")
                }
            }
        }
    }
}

fun saveImageToInternalStorage(context: Context, uri: Uri): String {
    val fileName = UUID.randomUUID().toString() + ".jpg"
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
    return fileName
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview
@Composable
private fun CollectionFormPreview() {
    MyBricksetTheme {
        CollectionForm(
            onDismissRequest = {}
        )
    }
}