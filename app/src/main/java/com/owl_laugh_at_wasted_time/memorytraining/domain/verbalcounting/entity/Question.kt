package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity

data class Question(
    val sum:Int,
    val visibleNumber:Int,
    val options:List<Int>
)
