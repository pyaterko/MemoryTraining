package com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity

interface Field {
    val size:Int
    fun getCell(position:Int):Boolean?
    fun get():Array<Boolean?>
}