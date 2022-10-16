package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameVerbalCountingRepository
import javax.inject.Inject

class GetGameSettingsUsecase @Inject constructor(
    private val repository: GameVerbalCountingRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}