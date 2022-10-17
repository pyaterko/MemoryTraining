package com.owl_laugh_at_wasted_time.memorytraining.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CellDbModel::class], version = 1, exportSchema = false)
abstract class CellDatabase : RoomDatabase() {
    abstract fun cellDao(): CellDao
}