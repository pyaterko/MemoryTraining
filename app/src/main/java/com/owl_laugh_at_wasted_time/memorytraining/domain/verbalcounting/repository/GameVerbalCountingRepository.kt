package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question

interface GameVerbalCountingRepository {
    fun generatyQuestion(
        maxValue: Int,
        countOfOptions: Int,
        operation: Operation,
        level: Level
    ): Question

    fun getGameSettings(level: Level): GameSettings
}