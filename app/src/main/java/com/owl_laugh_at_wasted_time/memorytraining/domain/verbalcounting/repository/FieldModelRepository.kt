package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository

import androidx.lifecycle.LiveData
import com.owl_laugh_at_wasted_time.memorytraining.data.CellDbModel
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell


interface FieldModelRepository {
    fun getList(): LiveData<List<Cell>>
    suspend fun addItem(item: Cell)
    suspend fun addList(list: List<Cell>)
    suspend fun delete()
    suspend fun activField(size: Int)
    suspend fun notActivField(size: Int)
}