package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameRepository


class GenerateQuestionUsecase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generatyQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}