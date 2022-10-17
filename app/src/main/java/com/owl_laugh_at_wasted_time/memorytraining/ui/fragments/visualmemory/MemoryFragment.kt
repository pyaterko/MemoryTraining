package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentMemoryBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding
import kotlinx.coroutines.delay

class MemoryFragment : BaseFragment(R.layout.fragment_memory) {

    private val binding: FragmentMemoryBinding by viewBinding(FragmentMemoryBinding::bind)
    private val viewModel by viewModels<MemoryFragmentViewModel> { viewModelFactory }

    private val args by navArgs<MemoryFragmentArgs>()

    private var field: List<Cell>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FieldRVAdapter()

        startGame(adapter)
        viewModel.getList.observe(viewLifecycleOwner) {
            if (field == null) {
                field = it
                adapter.items = it
            } else {
                adapter.items = it
            }

        }

        adapter.onItemClickListener = { cell->
                val x=cell
            if (field!![cell.id].focus) {
                viewModel.addItem(field!![cell.id])
            }
        }

    }

    private fun startGame(adapter: FieldRVAdapter) {
        when (args.level) {
            Level.EASY -> {
                binding.recyclerViewField.layoutManager =
                    GridLayoutManager(requireContext(), 6)
                binding.recyclerViewField.adapter = adapter
                launchScope {
                    viewModel.activField(36)
                    delay(3000)
                    viewModel.notActivField(36)
                }

            }
            Level.NORMAL -> {

            }
            Level.HARD -> {

            }
        }
    }
}