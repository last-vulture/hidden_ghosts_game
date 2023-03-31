package com.testtask.hiddenghosts.core.ui.design_system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.testtask.hiddenghosts.core.ui.theme.*


@Composable
fun HGButtonMain(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    HGButton(
        text = text,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(ContentDefault),
        contentPadding = PaddingValues(
            horizontal = buttonMainHorizontalPadding,
            vertical = buttonMainVerticalPadding
        ),
        onClick = onClick
    )
}

@Composable
fun HGButtonSecondary(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    HGButton(
        text = text,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(ContentSecondary),
        contentPadding = PaddingValues(
            horizontal = buttonSecondaryHorizontalPadding,
            vertical = buttonSecondaryVerticalPadding
        ),
        textColor = ContentDefault,
        onClick = onClick
    )
}

@Composable
fun HGButton(
    text: String,
    colors: ButtonColors,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    shape: Shape = Shapes.small,
    textStyle: TextStyle = Typography.button,
    textColor: Color = GameDefault,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}