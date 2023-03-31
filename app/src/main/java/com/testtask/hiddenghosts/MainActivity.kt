package com.testtask.hiddenghosts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.testtask.hiddenghosts.core.ui.design_system.HGBackground
import com.testtask.hiddenghosts.core.ui.theme.HiddenGhostsTheme
import com.testtask.hiddenghosts.features.NavGraphs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiddenGhostsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HGBackground()
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}