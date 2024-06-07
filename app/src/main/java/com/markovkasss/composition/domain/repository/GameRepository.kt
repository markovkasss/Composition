package com.markovkasss.composition.domain.repository

import com.markovkasss.composition.domain.entity.GameSettings
import com.markovkasss.composition.domain.entity.Level
import com.markovkasss.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}