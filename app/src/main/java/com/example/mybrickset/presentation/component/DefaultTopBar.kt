package com.example.mybrickset.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    onSearchTriggered: () -> Unit,
    onBackPressed: () -> Unit,
    backButtonState: MutableState<Boolean>,
    navigateToFavoriteScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(text = "")
        },
        navigationIcon = {
            if (backButtonState.value == false) {
                Image(
                    painter = painterResource(id = R.drawable.ic_lego),
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 8.dp)
                )
            } else {
                Button(
                    onClick = {
                        onBackPressed()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(4.dp),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onSearchTriggered() },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
            IconButton(
                onClick = { navigateToFavoriteScreen() },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        },
    )
}

@Preview
@Composable
private fun DefaultTopBarPreview() {
    MyBricksetTheme {

    }
}