package com.testtask.hiddenghosts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.testtask.hiddenghosts.ui.theme.HiddenGhostsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiddenGhostsTheme {
                HiddenGhostsApp()
            }
        }
    }
}