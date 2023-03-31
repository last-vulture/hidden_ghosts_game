package com.testtask.hiddenghosts.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val GameColorPalette = darkColors(
    primary = GameDefault,
    secondary = ContentDefault
)

@Composable
fun HiddenGhostsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = GameColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}