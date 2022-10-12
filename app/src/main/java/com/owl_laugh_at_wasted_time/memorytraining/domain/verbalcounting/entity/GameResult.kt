package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity

data class GameResult (
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQuestions:Int,
    val gameSettings: GameSettings
)
