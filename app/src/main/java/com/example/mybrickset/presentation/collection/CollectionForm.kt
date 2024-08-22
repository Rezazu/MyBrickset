package com.example.mybrickset.presentation.collection

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionForm(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var nameText by remember { mutableStateOf("") }
    var numberText by remember { mutableStateOf("") }
    var variantText by remember { mutableStateOf("") }

    var uriImage by remember { mutableStateOf<Uri?>(null) }
    var conditionText by remember { mutableStateOf("") }

    var acquiredDateText by remember { mutableStateOf("") }
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
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
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
                OutlinedTextField(
                    value = nameText,
                    onValueChange = { nameText = it },
                    label = { Text(text = "Name", style = MaterialTheme.typography.labelMedium) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = numberText,
                        onValueChange = { numberText = it },
                        label = { Text(text = "Set Number", style = MaterialTheme.typography.labelMedium) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
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
                        )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
                    ConditionDropDown(
                        onValueChanged = {},
                        text = conditionText
                    )
                }
//                ImageSelectionScreen()
                Button(
                    onClick = { launcher.launch("image/*") },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Select Image")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = acquiredDateText,
                        onValueChange = { acquiredDateText = it },
                        label = { Text(text = "Acquired Date", style = MaterialTheme.typography.labelMedium) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    )
                    HorizontalDivider(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = priceText,
                        onValueChange = { priceText = it },
                        label = { Text(text = "Price",style = MaterialTheme.typography.labelSmall) },
                        singleLine = true,
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
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Add Set")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionDropDown(
    onValueChanged: (String) -> Unit,
    text: String,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedText by remember {
        mutableStateOf(text)
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
                value = selectedText,
                onValueChange = { },
                label = { Text(text = "Condition", style = MaterialTheme.typography.labelSmall)
                        },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = Modifier
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Dummy.DummyCondition.forEach { condition ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = condition,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            selectedText = condition
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
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