package com.markovkasss.composition.domain.usecases

import com.markovkasss.composition.domain.entity.GameSettings
import com.markovkasss.composition.domain.entity.Level
import com.markovkasss.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}