package com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity

class ArrayField(override val size: Int) : MutableField {

   private val cells: Array<Boolean?> = arrayOfNulls(size*size)

    override fun get()=cells

    override fun setCell(position: Int, value: Boolean) {
        cells[position]=value
    }

    override fun getCell(position: Int): Boolean? = cells[position]


}