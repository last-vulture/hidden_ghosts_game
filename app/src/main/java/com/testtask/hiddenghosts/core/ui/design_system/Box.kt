package com.testtask.hiddenghosts.core.ui.design_system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.testtask.hiddenghosts.core.ui.theme.screenPadding

@Composable
fun HGBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(screenPadding),
        contentAlignment = Alignment.Center,
        content = content,
    )
}