package com.example.mybrickset.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    val focusRequester = remember { FocusRequester() }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .alpha(0.5f),
                    color = Color.White
                )
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch
                    navigateToSearchScreen()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                cursorColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    MyBricksetTheme {
        SearchBar(
            query = "Searching",
            onSearch = {},
            onQueryChange = {},
            navigateToSearchScreen = {},
            onCloseClicked = {}
        )
    }
}
