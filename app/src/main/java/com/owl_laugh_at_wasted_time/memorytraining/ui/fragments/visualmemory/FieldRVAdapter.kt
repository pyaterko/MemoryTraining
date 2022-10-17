package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.memorytraining.databinding.ItemFieldCellBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell
class FieldDiffCallBack(
    private val oldItem: List<Cell>,
    private val newItem: List<Cell>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItem[oldItemPosition].id == newItem[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItem[oldItemPosition] == newItem[newItemPosition]
}

class FieldRVAdapter() : RecyclerView.Adapter<Vh>() {

    var onItemClickListener: ((Cell) -> Unit)? = null

    var items: List<Cell> = emptyList()
        set(value) {
            val diffCallBack = FieldDiffCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFieldCellBinding.inflate(inflater, parent, false)
        return Vh(binding,parent.context)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val cell = items[position]
        holder.bind(cell)
        holder.binding.cellTv.setOnClickListener {
            onItemClickListener?.invoke(cell)
        }
    }

    override fun getItemCount(): Int = items.size
}

class Vh(
    val binding: ItemFieldCellBinding,
    val context: Context
) : RecyclerView.ViewHolder(
    binding.root
) {
     fun bind(cell: Cell) {
           binding.cellTv.setBackgroundColor( ContextCompat.getColor(context, cell.currentState.toMark()))
     }
}

fun Boolean.toMark(): Int = when (this) {
    true -> android.R.color.holo_red_dark
    false -> android.R.color.darker_gray
}