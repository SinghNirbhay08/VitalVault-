package com.nirbhay.vitalvault01.ui.theme


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = BrandWhite,
    secondary = BrandDarkGray,
    tertiary = BrandPrimary,
    background = BrandWhite,
    onBackground = BrandBlack,
    surface = BrandWhite,
    onSurface = BrandBlack,
    surfaceVariant = BrandLightBlue,
    onSurfaceVariant = BrandDarkGray,
    outline = BrandDarkGray
)

@Composable
fun VitalVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // We are forcing Light Theme for this MVP to match your design
    val colorScheme = LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // FIX: Set status bar to White and Icons to Black
            window.statusBarColor = Color.White.toArgb()

            // isAppearanceLightStatusBars = true -> Means "My status bar is light, so give me DARK/BLACK icons"
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}