package com.example.mybrickset.presentation.collection

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.data.local.SetCollection
import com.example.mybrickset.presentation.component.ConditionDropDown
import com.example.mybrickset.presentation.component.DatePickerForm
import com.example.mybrickset.presentation.component.NumberForm
import com.example.mybrickset.presentation.component.TextForm
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red

@Composable
fun CollectionForm(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit,
) {

    val context = LocalContext.current
    val nameInput = viewModel.nameInput.collectAsState()
    val numberInput = viewModel.numberInput.collectAsState()
    val variantInput = viewModel.variantInput.collectAsState()
    val imageInput = viewModel.imageInput.value
    val conditionInput = viewModel.conditionInput.collectAsState()
    val dateInput = viewModel.acquiredDateInput.collectAsState()
    val priceInput = viewModel.priceInput.collectAsState()
    val setId = viewModel.setId.collectAsState()

    val editState by remember {
        mutableStateOf(
            if (setId.value.isNotEmpty()) {
                true
            } else {
                false
            }
        )
    }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract =
            ActivityResultContracts.PickVisualMedia(),
            onResult = { uri: Uri? ->
                viewModel.onImageInputChange(uri.toString())
                Log.d("URI RESULT", "$uri")
            }
        )

    var hasPermission by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasPermission = isGranted
    }

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
                    NumberForm(
                        numberInput = numberInput.value,
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
                    NumberForm(
                        numberInput = variantInput.value,
                        label = "",
                        onValueChange = viewModel::onVariantInputChange
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (imageInput != "") {
                        AsyncImage(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(150.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .placeholder(R.drawable.img_placeholder)
                                .data(Uri.parse(imageInput))
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    ConditionDropDown(
                        condition = conditionInput.value,
                        onValueChanged = viewModel::onConditionInputChange,
                    )
                }
                Button(
                    onClick = {
                                if (!hasPermission) {
                                    launcher.launch(
                                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                                    )
                                }
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
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
                    Spacer(modifier = Modifier.width(16.dp))
                    NumberForm(
                        numberInput = priceInput.value,
                        label = "Price",
                        onValueChange = viewModel::onPriceInputChange
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 32.dp,
                            bottom = 16.dp,
                        )
                ) {
                    Button(
                        onClick = { onDismissRequest() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Red),
                        modifier = Modifier
                            .wrapContentWidth()
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        imageInput.let {
                            saveImage(context, Uri.parse(it))
                        }

                        if (listOf(
                                nameInput.value,
                                numberInput.value,
                                variantInput.value,
                                imageInput,
                                dateInput.value,
                                priceInput.value,
                            ).all { it.isNotEmpty() }) {
                            viewModel.insertSetCollection(editState = editState)
                            onDismissRequest()
                        } else {
                            Toast.makeText(context, "Please fill all the form", Toast.LENGTH_SHORT).show()
                        }
                    },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .wrapContentWidth()

                    ) {
                        Text(
                            text = if(editState) "Edit Set" else "Add Set"
                        )
                    }
                }
            }
        }
    }
}

fun saveImage(context: Context, uri: Uri) {
    if (uri.toString().isNotEmpty()) {

        val flags =Intent.FLAG_GRANT_READ_URI_PERMISSION
        val resolver = context.contentResolver
        try {
            resolver.takePersistableUriPermission(uri, flags)
        } catch (e: SecurityException) {
            Log.e("SaveImage", "Failed to take persistable permission", e)
            // Handle the exception, e.g., show an error message to the user
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