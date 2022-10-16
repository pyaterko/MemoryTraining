package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Field

class FieldRVAdapter(
    private val field: Field,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<Vh>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Vh(parent, onClick)
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(field.getCell(position), position)
    }

    override fun getItemCount(): Int = field.size * field.size
}

class Vh(
    parent: ViewGroup,
    onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_field_cell, parent, false)
) {
    private val tv = itemView.findViewById<TextView>(R.id.cell_tv)
    private var index = -1

    init {
        tv.setOnClickListener {
            onClick(index)
        }
    }

    fun bind(cell: Boolean?, position: Int) {
        index = position
        tv.text = cell.toMark()
    }
}

fun Boolean?.toMark(): String = when (this) {
    true -> "X"
    false -> "O"
    null -> ""
}