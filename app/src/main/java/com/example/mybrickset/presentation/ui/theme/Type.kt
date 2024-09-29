package com.example.mybrickset.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mybrickset.R

// Set of Material typography styles to start with

object AppFont {
    val OpenSans = FontFamily(
        Font(R.font.opensans_light),
        Font(R.font.opensans_regular),
        Font(R.font.opensans_italic, style = FontStyle.Italic),
        Font(R.font.opensans_semibold, FontWeight.SemiBold),
        Font(R.font.opensans_bold, FontWeight.Bold),
        Font(R.font.opensans_extrabold, FontWeight.ExtraBold),
    )
}
private val defaultTypography = Typography()

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
//    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.OpenSans),
//    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.OpenSans),
//    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.OpenSans),
//
//    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.OpenSans),
//    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.OpenSans),
//    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.OpenSans),
//
//    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.OpenSans),
//    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.OpenSans),
//    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.OpenSans),
//
//    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.OpenSans),
//    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.OpenSans),
//
//    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.OpenSans),
//    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.OpenSans),
//    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.OpenSans)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)