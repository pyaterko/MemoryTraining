package com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity

data class Cell(
    val id: Int,
    val defaultState: Boolean,
    val currentState: Boolean
)
