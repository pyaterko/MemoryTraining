package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentVerbalCountingGameBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class VerbalCountingGameFragment : BaseFragment(R.layout.fragment_verbal_counting_game) {

    private val binding by viewBinding(FragmentVerbalCountingGameBinding::bind)
    private val viewModel by viewModels<VerbalCountingGameViewModel> { viewModelFactory }

    private lateinit var level: Level

    val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        level = requireArguments().getParcelable<Level>("LEVEL") as Level
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obserbViewModel()
        viewModel.startGame(level)

    }

    private fun obserbViewModel() {
        viewModel.apply {
            question.observe(viewLifecycleOwner) {
                binding.firstNumber.text = it.firstNumber.toString()
                binding.secondNumber.text = it.secondNumber.toString()
                for (i in 0 until tvOptions.size) {
                    tvOptions[i].text = it.options[i].toString()
                }
            }
        }
    }
}