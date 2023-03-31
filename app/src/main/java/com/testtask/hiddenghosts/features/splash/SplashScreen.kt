package com.testtask.hiddenghosts.features.splash

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testtask.hiddenghosts.R
import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.ui.design_system.HGBox
import com.testtask.hiddenghosts.features.destinations.GameMenuScreenDestination
import com.testtask.hiddenghosts.features.destinations.SplashScreenDestination
import kotlinx.coroutines.delay


@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    LaunchedEffect(key1 = null) {
        delay(GameConfig.splashScreenTimeoutMillis)

        navigator.navigate(GameMenuScreenDestination) {
            popUpTo(SplashScreenDestination.route) {
                inclusive = true
            }
        }
    }

    HGBox {
        Image(
            painter = painterResource(id = R.drawable.ic_game_icon),
            contentDescription = stringResource(R.string.content_description_game_logo)
        )
    }
}