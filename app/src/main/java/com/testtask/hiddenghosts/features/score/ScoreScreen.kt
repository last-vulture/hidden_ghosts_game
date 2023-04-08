package com.testtask.hiddenghosts.features.score

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testtask.hiddenghosts.R
import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.data.Level
import com.testtask.hiddenghosts.core.ui.design_system.HGCenterBox
import com.testtask.hiddenghosts.core.ui.design_system.HGButtonMain
import com.testtask.hiddenghosts.core.ui.design_system.HGTextMain
import com.testtask.hiddenghosts.core.ui.design_system.HGTextSecondary
import com.testtask.hiddenghosts.features.destinations.GameMenuScreenDestination
import com.testtask.hiddenghosts.features.destinations.GameScreenDestination

@Destination
@Composable
fun ScoreScreen(
    score: Int,
    level: Level,
    navigator: DestinationsNavigator
) {
    val requiredScore = level.ghostsCount * GameConfig.pointsPerGhost
    val isLevelPassed = requiredScore == score

    val nextLevel = if (isLevelPassed) {
        GameConfig.getNextLevel(level)
    } else {
        level
    }

    HGCenterBox {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HGTextMain(text = "$score")
            HGTextSecondary(text = stringResource(id = R.string.title_score))
        }

        HGButtonMain(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(id = R.string.action_next_level)
        ) {
            navigator.navigate(GameScreenDestination(level = nextLevel)) {
                popUpTo(GameMenuScreenDestination.route)
            }
        }
    }
}