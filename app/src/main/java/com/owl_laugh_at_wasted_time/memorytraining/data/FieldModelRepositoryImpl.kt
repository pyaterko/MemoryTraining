package com.owl_laugh_at_wasted_time.memorytraining.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.FieldModelRepository
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell
import javax.inject.Inject
import kotlin.random.Random

class FieldModelRepositoryImpl @Inject constructor(
    private val cellDao: CellDao,
    private val mapper: CellMapper
) : FieldModelRepository {

    override suspend fun activField(size: Int) {
        val list = mutableListOf<Cell>()
        for (i in 0..size - 1) {
            val state = Random.nextBoolean()
            list.add(Cell(i, state, state))
        }
        addList(list)
    }

    override fun getList(): LiveData<List<Cell>> =
        Transformations.map(
            cellDao.getList()
        ) {
            mapper.mapListDbModelToListEntity(it)
        }

    override suspend fun addItem(item: Cell) {
        cellDao.addItem(mapper.mapEntityToDbModel(item))
    }

    override suspend fun addList(list: List<Cell>) {
        cellDao.addList(mapper.mapListEntityToListDbModel(list))
    }

    override suspend fun delete() {
        cellDao.delete()
    }
}