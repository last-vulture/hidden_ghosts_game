package com.testtask.hiddenghosts.features.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testtask.hiddenghosts.R
import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.ui.design_system.HGCenterBox
import com.testtask.hiddenghosts.core.ui.design_system.HGButtonMain
import com.testtask.hiddenghosts.features.destinations.GameScreenDestination

@Destination
@Composable
fun GameMenuScreen(
    navigator: DestinationsNavigator
) {
    HGCenterBox {
        HGButtonMain(text = stringResource(id = R.string.action_start_game)) {
            navigator.navigate(GameScreenDestination(level = GameConfig.levels.first()))
        }
    }
}