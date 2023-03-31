package com.testtask.hiddenghosts.features.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.hiddenghosts.R
import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.data.Level
import com.testtask.hiddenghosts.core.ui.theme.GameCellCorrect
import com.testtask.hiddenghosts.core.ui.theme.GameCellDefault
import com.testtask.hiddenghosts.core.ui.theme.GameCellWrong
import com.testtask.hiddenghosts.features.game.state.GameCellState
import com.testtask.hiddenghosts.features.game.state.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameState.defaultState())
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    private var cellClicksCounter = 0
    private var isGameStarted = false

    fun resetGame(level: Level) {
        cellClicksCounter = 0

        val gameState = GameState(
            score = 0,
            level = level,
            isGameEnd = false,
            gameCellStates = generateGameCellsForLevel(level)
        )

        _uiState.value = gameState

        viewModelScope.launch {
            delay(GameConfig.onGameStartBoardPreviewMillis)
            previewGhostPositions()
            delay(GameConfig.ghostsVisibilityTimeoutMillis)
            hideGhostPositions()
            isGameStarted = true
        }
    }

    fun onCellClick(cell: GameCellState) {
        if (!isGameStarted || cell.isGhostVisible)
            return

        cellClicksCounter++

        updateScoreOnCellClick(cell)
        markCellAsClicked(cell)
        updateGameEndedState()
    }

    private fun updateScoreOnCellClick(cell: GameCellState) {
        if (cell.hasGhost) {
            val oldScore = uiState.value.score
            val newScore = oldScore + GameConfig.pointsPerGhost

            _uiState.value = uiState.value.copy(score = newScore)
        }
    }

    private fun updateGameEndedState() {
        if (uiState.value.level.ghostsCount == cellClicksCounter) {
            isGameStarted = false

            previewGhostPositions()
            _uiState.value = _uiState.value.copy(isGameEnd = true)
        }
    }

    private fun previewGhostPositions() {
        val updatedGameCellStates = mutableListOf<GameCellState>()

        uiState.value.gameCellStates.onEach { cell ->
            updatedGameCellStates.add(
                cell.copy(isGhostVisible = true)
            )
        }

        _uiState.value = uiState.value.copy(gameCellStates = updatedGameCellStates)
    }

    private fun hideGhostPositions() {
        val updatedGameCellStates = mutableListOf<GameCellState>()

        uiState.value.gameCellStates.onEach { cell ->
            updatedGameCellStates.add(
                cell.copy(isGhostVisible = false)
            )
        }

        _uiState.value = uiState.value.copy(gameCellStates = updatedGameCellStates)
    }

    private fun markCellAsClicked(clickedCell: GameCellState) {
        val oldGameCellStates = uiState.value.gameCellStates

        val updatedGameCellStates = mutableListOf<GameCellState>()
        updatedGameCellStates.addAll(oldGameCellStates)

        val updatedCellColor = if (clickedCell.hasGhost) {
            GameCellCorrect
        } else {
            GameCellWrong
        }

        val updatedGhostDrawableRes = if (clickedCell.hasGhost) {
            clickedCell.ghostDrawableRes
        } else {
            R.drawable.ic_ghost_wrong
        }

        updatedGameCellStates[clickedCell.index] = oldGameCellStates[clickedCell.index].copy(
            isGhostVisible = true,
            cellColor = updatedCellColor,
            ghostDrawableRes = updatedGhostDrawableRes
        )

        _uiState.value = uiState.value.copy(gameCellStates = updatedGameCellStates)
    }

    private fun generateGameCellsForLevel(level: Level): List<GameCellState> {
        val gridSize = level.gridSize.first * level.gridSize.second
        val ghostCount = level.ghostsCount
        val ghostPositions = generateGhostPositions(gridSize, ghostCount)

        val gameCellStates = mutableListOf<GameCellState>()

        for (index in 0 until gridSize) {
            val hasGhost = ghostPositions.contains(index)

            val cellColor = if (hasGhost) GameCellDefault else Color.Transparent
            val ghostDrawableRes = if (hasGhost) GameConfig.ghostDrawableResList.random() else null

            gameCellStates.add(
                GameCellState(
                    index = index,
                    cellColor = cellColor,
                    hasGhost = hasGhost,
                    isGhostVisible = false,
                    ghostDrawableRes = ghostDrawableRes,
                )
            )

        }

        return gameCellStates
    }

    private fun generateGhostPositions(gridSize: Int, ghostsCount: Int): Set<Int> {
        val positions = mutableSetOf<Int>()
        while (positions.size < ghostsCount) {
            positions.add((0 until gridSize).random())
        }
        return positions
    }

}