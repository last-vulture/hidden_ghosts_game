package com.testtask.hiddenghosts.features.game.state

import com.testtask.hiddenghosts.core.data.GameConfig
import com.testtask.hiddenghosts.core.data.Level

data class GameState(
    val score: Int,
    val level: Level,
    val isGameEnd: Boolean,
    val gameCellStates: List<GameCellState>,
) {
    companion object {
        fun defaultState() = GameState(
            score = 0,
            level = GameConfig.levels.first(),
            isGameEnd = false,
            gameCellStates = emptyList(),
        )
    }
}