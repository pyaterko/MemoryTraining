package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameVerbalCountingRepository

class GetGameSettingsUsecase(
    private val repository: GameVerbalCountingRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}