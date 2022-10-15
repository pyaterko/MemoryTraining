package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository

import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Field


interface Game {
    val check:Boolean?
    val field: Field
    fun move(position:Int):Int

}

