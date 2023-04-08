package com.testtask.hiddenghosts.core.data

import com.testtask.hiddenghosts.R

object GameConfig {
    const val splashScreenTimeoutMillis = 1000L
    const val ghostsVisibilityTimeoutMillis = 2500L
    const val onGameEndBoardPreviewMillis = 1500L
    const val onGameStartBoardPreviewMillis = 500L

    const val pointsPerGhost = 5

    val levels = listOf(
        Level(
            gridSize = Pair(4, 4),
            ghostsCount = 4
        ),
        Level(
            gridSize = Pair(4, 5),
            ghostsCount = 5
        ),
        Level(
            gridSize = Pair(4, 6),
            ghostsCount = 6
        ),
        Level(
            gridSize = Pair(5, 6),
            ghostsCount = 7
        ),
        Level(
            gridSize = Pair(5, 7),
            ghostsCount = 8
        )
    )

    val ghostDrawableResList = listOf(
        R.drawable.ic_ghost_1,
        R.drawable.ic_ghost_2,
        R.drawable.ic_ghost_3,
        R.drawable.ic_ghost_4,
        R.drawable.ic_ghost_5,
    )

    fun getNextLevel(level: Level) : Level {
        val nextLevelIndex = levels.indexOf(level) + 1
        return levels.getOrElse(nextLevelIndex) {
            levels.first()
        }
    }
}