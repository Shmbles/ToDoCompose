package com.azrael.to_docompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import md_theme_dark_background
import md_theme_dark_error
import md_theme_dark_errorContainer
import md_theme_dark_inverseOnSurface
import md_theme_dark_inversePrimary
import md_theme_dark_inverseSurface
import md_theme_dark_onBackground
import md_theme_dark_onError
import md_theme_dark_onErrorContainer
import md_theme_dark_onPrimary
import md_theme_dark_onPrimaryContainer
import md_theme_dark_onSecondary
import md_theme_dark_onSecondaryContainer
import md_theme_dark_onSurface
import md_theme_dark_onSurfaceVariant
import md_theme_dark_onTertiary
import md_theme_dark_onTertiaryContainer
import md_theme_dark_outline
import md_theme_dark_outlineVariant
import md_theme_dark_primary
import md_theme_dark_primaryContainer
import md_theme_dark_scrim
import md_theme_dark_secondary
import md_theme_dark_secondaryContainer
import md_theme_dark_surface
import md_theme_dark_surfaceTint
import md_theme_dark_surfaceVariant
import md_theme_dark_tertiary
import md_theme_dark_tertiaryContainer
import md_theme_light_background
import md_theme_light_error
import md_theme_light_errorContainer
import md_theme_light_inverseOnSurface
import md_theme_light_inversePrimary
import md_theme_light_inverseSurface
import md_theme_light_onBackground
import md_theme_light_onError
import md_theme_light_onErrorContainer
import md_theme_light_onPrimary
import md_theme_light_onPrimaryContainer
import md_theme_light_onSecondary
import md_theme_light_onSecondaryContainer
import md_theme_light_onSurface
import md_theme_light_onSurfaceVariant
import md_theme_light_onTertiary
import md_theme_light_onTertiaryContainer
import md_theme_light_outline
import md_theme_light_outlineVariant
import md_theme_light_primary
import md_theme_light_primaryContainer
import md_theme_light_scrim
import md_theme_light_secondary
import md_theme_light_secondaryContainer
import md_theme_light_surface
import md_theme_light_surfaceTint
import md_theme_light_surfaceVariant
import md_theme_light_tertiary
import md_theme_light_tertiaryContainer

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    outline = md_theme_light_outline,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    inverseSurface = md_theme_light_inverseSurface,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    outline = md_theme_dark_outline,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    inverseSurface = md_theme_dark_inverseSurface,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

@Composable
fun ToDoComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val view = LocalView.current
    val colorPrimary = colors.primary
    val colorSurface = colors.surface

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorPrimary.toArgb()
            window.navigationBarColor = colorSurface.toArgb()
            val wic = WindowCompat.getInsetsController(window, view)
            wic.isAppearanceLightStatusBars = darkTheme
            wic.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}