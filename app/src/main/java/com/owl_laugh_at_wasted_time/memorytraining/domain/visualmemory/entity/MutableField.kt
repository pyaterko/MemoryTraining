package com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity

interface MutableField : Field {
    fun setCell(position: Int, value: Boolean)
}