package com.testtask.hiddenghosts.core.data

import java.io.Serializable

data class Level(
    val gridSize: Pair<Int, Int>, //column, row
    val ghostsCount: Int
) : Serializable
