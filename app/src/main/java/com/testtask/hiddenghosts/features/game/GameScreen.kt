package com.testtask.hiddenghosts.features.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testtask.hiddenghosts.R
import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.data.Level
import com.testtask.hiddenghosts.core.ui.design_system.HGCenterBox
import com.testtask.hiddenghosts.core.ui.design_system.HGButtonSecondary
import com.testtask.hiddenghosts.core.ui.design_system.HGTextSecondary
import com.testtask.hiddenghosts.core.ui.theme.CellBackground
import com.testtask.hiddenghosts.core.ui.theme.elementsPadding
import com.testtask.hiddenghosts.core.ui.theme.gameBoardCellsPadding
import com.testtask.hiddenghosts.features.destinations.GameMenuScreenDestination
import com.testtask.hiddenghosts.features.destinations.ScoreScreenDestination
import com.testtask.hiddenghosts.features.game.state.GameCellState
import kotlinx.coroutines.delay

@Destination
@Composable
fun GameScreen(
    level: Level,
    navigator: DestinationsNavigator,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameState by gameViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = level) {
        gameViewModel.resetGame(level)
    }

    HGCenterBox {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HGTextSecondary(text = "${gameState.score}")
            Spacer(modifier = Modifier.padding(elementsPadding))
            HGButtonSecondary(
                text = stringResource(R.string.action_restart),
                onClick = {
                    gameViewModel.resetGame(level)
                }
            )
        }

        val gridColumnsCount = gameState.level.gridSize.first
        GameBoardGrid(
            modifier = Modifier.align(Alignment.Center),
            columnCount = gridColumnsCount,
            gameCellStates = gameState.gameCellStates,
            onCellClick = { cell ->
                gameViewModel.onCellClick(cell)
            }
        )

        AnimatedVisibility(
            visible = gameState.isGameEnd,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            GameEnded(
                level = level,
                score = gameState.score,
                navigator = navigator
            )
        }
    }
}

@Composable
fun GameEnded(level: Level, score: Int, navigator: DestinationsNavigator) {
    LaunchedEffect(key1 = level) {
        delay(GameConfig.onGameEndBoardPreviewMillis)

        navigator.navigate(
            ScoreScreenDestination(
                score = score,
                level = level
            )
        ) {
            popUpTo(GameMenuScreenDestination.route)
        }
    }

    val gameEndDrawableRes = if (GameConfig.isLevelPassed(level, score)) {
        R.drawable.ic_level_passed
    } else {
        R.drawable.ic_level_failed
    }

    Image(
        painter = painterResource(id = gameEndDrawableRes),
        contentDescription = stringResource(R.string.content_description_end_game_result)
    )
}

@Composable
fun GameBoardGrid(
    columnCount: Int,
    gameCellStates: List<GameCellState>,
    modifier: Modifier = Modifier,
    onCellClick: (GameCellState) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columnCount),
        verticalArrangement = Arrangement.spacedBy(gameBoardCellsPadding),
        horizontalArrangement = Arrangement.spacedBy(gameBoardCellsPadding)
    ) {
        items(items = gameCellStates) { cell ->
            val state = remember {
                MutableTransitionState(false).apply {
                    targetState = true
                }
            }
            AnimatedVisibility(
                visibleState = state
            ) {
                GridCellItem(
                    cell = cell,
                    onClick = {
                        onCellClick.invoke(cell)
                    }
                )
            }
        }
    }
}

@Composable
fun GridCellItem(
    cell: GameCellState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(CellBackground)
            .aspectRatio(1f)
            .clickable {
                onClick.invoke()
            }
    ) {
        AnimatedVisibility(
            visible = cell.isGhostVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(cell.cellColor)
            )

            cell.ghostDrawableRes?.let { drawableRes ->
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = stringResource(R.string.content_description_ghost_cell),
                )
            }
        }
    }
}
