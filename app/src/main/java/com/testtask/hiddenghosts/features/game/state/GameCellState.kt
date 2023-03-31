package com.testtask.hiddenghosts.features.game.state

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.testtask.hiddenghosts.core.ui.theme.CellBackground

data class GameCellState(
    val index: Int,
    val hasGhost: Boolean,
    val cellColor: Color,
    val isGhostVisible: Boolean,
    @DrawableRes
    val ghostDrawableRes: Int?,
) {
    companion object {
        fun defaultState() = GameCellState(
            index = 0,
            hasGhost = false,
            isGhostVisible = false,
            ghostDrawableRes = null,
            cellColor = CellBackground,
        )
    }
}