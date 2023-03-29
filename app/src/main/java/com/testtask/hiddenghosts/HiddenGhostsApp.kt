package com.testtask.hiddenghosts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HiddenGhostsApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        //empty
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun AppPreview() {
    HiddenGhostsApp()
}