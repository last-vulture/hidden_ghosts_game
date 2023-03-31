package com.testtask.hiddenghosts.core.ui.design_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.testtask.hiddenghosts.R

@Composable
fun HGBackground() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.content_description_app_background)
    )
}