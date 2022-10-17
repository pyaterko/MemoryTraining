package com.owl_laugh_at_wasted_time.memorytraining.data

import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell
import javax.inject.Inject


class CellMapper @Inject constructor() {

    fun mapEntityToDbModel(item: Cell) = CellDbModel(
        id = item.id,
        currentState = item.currentState,
        defaultState = item.defaultState
    )

    fun mapDbModelToEntity(item: CellDbModel) = Cell(
        id = item.id,
        currentState = item.currentState,
        defaultState = item.defaultState
    )

    fun mapListDbModelToListEntity(list: List<CellDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    fun mapListEntityToListDbModel(list: List<Cell>) = list.map {
        mapEntityToDbModel(it)
    }
}