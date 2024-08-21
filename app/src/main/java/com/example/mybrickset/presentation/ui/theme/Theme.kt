package com.example.mybrickset.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = YellowMain,
    secondary = YellowMain,
    secondaryContainer = LightYellow,
    onSecondaryContainer = YellowMain,
    onSurface = YellowMain,
    onSurfaceVariant = LightGray,
    background = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = YellowMain,
    secondary = Blue,
    tertiary = LightYellow,
    primaryContainer = YellowMain,
    onPrimaryContainer = Color.White,
    secondaryContainer = YellowMain,
    onSecondaryContainer = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    onSurface = DarkGray,
    onSurfaceVariant = DarkGray,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyBricksetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}