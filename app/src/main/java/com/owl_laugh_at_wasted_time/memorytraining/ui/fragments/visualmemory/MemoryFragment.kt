package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
    private var easy = 4
    private var medium = 6
    private var hard = 8

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FieldRVAdapter()

        startGame(adapter)
        viewModel.getList.observe(viewLifecycleOwner) {
            field = it.toList()
            adapter.items = it
        }

        adapter.onItemClickListener = { cell ->
            if (cell.defaultState == true) {
                viewModel.addItem(cell.copy(currentState = true))
                launchScope {
                    delay(1000)
                    if (finish()) showFinishDialog(adapter)
                }
            } else {
                field?.forEach {
                    viewModel.addItem(it.copy(currentState = it.defaultState))
                }
                showFinishDialog(adapter)
            }
        }
    }

    private fun finish(): Boolean {
        field?.forEach {
            if (it.defaultState != it.currentState) {
                return false
            }
        }
        return true
    }

    private fun showFinishDialog(adapter: FieldRVAdapter) {
        displayAConfirmationDialog(requireContext(),
            actionNB1 = {
                findNavController().navigateUp()
            },
            actionPB1 = {
                startGame(adapter)
            })
    }

    private fun startGame(adapter: FieldRVAdapter) {
        when (args.level) {
            Level.EASY -> {
                binding.recyclerViewField.layoutManager =
                    GridLayoutManager(requireContext(), easy)
                binding.recyclerViewField.adapter = adapter
                launchScope {
                    viewModel.activField(easy * easy)
                    delay(5000)
                    field?.forEach {
                        viewModel.addItem(it.copy(currentState = false))
                    }
                }
            }
            Level.NORMAL -> {
                binding.recyclerViewField.layoutManager =
                    GridLayoutManager(requireContext(), medium)
                binding.recyclerViewField.adapter = adapter
                launchScope {
                    viewModel.activField(medium * medium)
                    delay(5000)
                    field?.forEach {
                        viewModel.addItem(it.copy(currentState = false))
                    }
                }
            }
            Level.HARD -> {
                binding.recyclerViewField.layoutManager =
                    GridLayoutManager(requireContext(), hard)
                binding.recyclerViewField.adapter = adapter
                launchScope {
                    viewModel.activField(hard * hard)
                    delay(5000)
                    field?.forEach {
                        viewModel.addItem(it.copy(currentState = false))
                    }
                }
            }
        }
    }

}