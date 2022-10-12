package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question

interface GameRepository {
    fun generatyQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}