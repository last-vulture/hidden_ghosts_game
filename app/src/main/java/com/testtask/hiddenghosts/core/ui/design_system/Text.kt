package com.testtask.hiddenghosts.core.ui.design_system

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.testtask.hiddenghosts.core.ui.theme.ContentDefault
import com.testtask.hiddenghosts.core.ui.theme.Typography

@Composable
fun HGTextMain(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = Typography.h1,
        color = ContentDefault
    )
}

@Composable
fun HGTextSecondary(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = Typography.h4,
        color = ContentDefault
    )
}