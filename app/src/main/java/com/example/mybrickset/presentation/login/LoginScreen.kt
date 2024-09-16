package com.example.mybrickset.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.YellowMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHomeScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val loggedIn by viewModel.loggedIn.collectAsState()

    val usernameText by viewModel.onUsernameTextChange.collectAsState()
    val passwordText by viewModel.onPasswordTextChange.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
    ) {
        Text(
            text = "Sign In To Your Account",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                )
        )
        Image(
            painter = painterResource(id = R.drawable.img_emmet),
            contentDescription = null,
            modifier = Modifier
                .wrapContentHeight()
                .width(180.dp)
                .align(Alignment.TopEnd)
                .padding(
                    top = 72.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 32.dp
                )
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        15.dp,
                        15.dp,
                        0.dp,
                        0.dp
                    )
                )
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 32.dp)
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = "Username",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = FontFamily.SansSerif
                        ),
                        color = Color.Black,
                    )
                    OutlinedTextField(
                        value = usernameText,
                        onValueChange = {
                            viewModel.onUsernameTextChange(it)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = YellowMain,
                            focusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.LightGray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(72.dp)
                            .padding(vertical = 8.dp)
                    )
                }
                Column {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = FontFamily.SansSerif
                        ),
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = passwordText,
                        onValueChange = {
                            viewModel.onPasswordTextChange(it)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = YellowMain,
                            focusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.LightGray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                painterResource(id = R.drawable.ic_visible)
                            else painterResource(id = R.drawable.ic_invisible)

                            val description =
                                if (passwordVisible) "Hide password" else "Show password"

                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    painter = image,
                                    contentDescription = description,
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(72.dp)
                            .padding(vertical = 8.dp)
                    )
                }

                Button(
                    onClick = {
//                        if (usernameText.isNotEmpty() && passwordText.isNotEmpty()) {
//                            viewModel.login(
//                                usernameText,
//                                passwordText
//                            )
//                        } else {
//                            Toast.makeText(context, "Username or Password cannot be empty", Toast.LENGTH_SHORT).show()
//                        }
                        navigateToHomeScreen()
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                ) {
                    Text(
                        text = "Sign in",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.logo_brickset_transparent),
                contentDescription = null,
                modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()
                    .width(210.dp)
            )
        }
    }

    LaunchedEffect(loggedIn.message) {
        if (loggedIn.message.isNotEmpty()) {
            Toast.makeText(context, loggedIn.message, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(loggedIn) {
        if (loggedIn.isLoggedIn == true) {
            navigateToHomeScreen()
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    MyBricksetTheme {
        LoginScreen(
            navigateToHomeScreen = {

            }
        )
    }
}