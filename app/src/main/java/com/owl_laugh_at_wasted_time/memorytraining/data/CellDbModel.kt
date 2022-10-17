package com.owl_laugh_at_wasted_time.memorytraining.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CellDbModelTable")
data class CellDbModel(
    @PrimaryKey
    val id: Int,
    val defaultState: Boolean,
    val currentState: Boolean
)
