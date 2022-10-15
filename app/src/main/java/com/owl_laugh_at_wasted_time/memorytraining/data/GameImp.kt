package com.owl_laugh_at_wasted_time.memorytraining.data

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.Game
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.ArrayField
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.MutableField

class GameImp(
    size: Int,
) : Game {
    override val check: Boolean? = null
    override val field: MutableField = ArrayField(size)

    override fun move(position: Int): Int {
        TODO("Not yet implemented")
    }

    private val array = field.get()

}