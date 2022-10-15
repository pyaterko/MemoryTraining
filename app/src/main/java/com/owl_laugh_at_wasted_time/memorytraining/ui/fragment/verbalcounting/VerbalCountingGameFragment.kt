package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        level = VerbalCountingGameFragmentArgs.fromBundle(requireArguments()).level
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obserbViewModel()
        setOnClickListenerToOptions()
        viewModel.startGame(level)

    }

    private fun setOnClickListenerToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooeAnswee(tvOption.text.toString().toInt())
            }
        }
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

            percentOfRightAnswers.observe(viewLifecycleOwner) {
                binding.progressBar.setProgress(it, true)
            }

            enoughPercentCorrectAnswers.observe(viewLifecycleOwner) {
                val color = getColorByState(it)
                binding.progressBar.progressTintList = ColorStateList.valueOf(color)
            }

            enoughCorrectAnswers.observe(viewLifecycleOwner) {
                binding.tvAnswersProgress.setTextColor(getColorByState(it))
            }

            timeLiveData.observe(viewLifecycleOwner) {

                binding.tvTimer.text = it
            }
            minPercent.observe(viewLifecycleOwner) {
                binding.progressBar.secondaryProgress = it
            }

            gameResult.observe(viewLifecycleOwner) {
                findNavController().navigate(
                  VerbalCountingGameFragmentDirections.actionVerbalCountingGameFragmentToGameFinishedFragment(it)
                )
            }
            progressAnswers.observe(viewLifecycleOwner) {
                binding.tvAnswersProgress.text = it
            }
            colorTimer.observe(viewLifecycleOwner){

                binding.tvTimer.setTextColor(getColorByState(it>10))
            }
        }

    }

    private fun getColorByState(state: Boolean): Int {
        val colorResId = if (state) {
            android.R.color.holo_green_dark
        } else {
            android.R.color.holo_red_dark
        }
        val color = ContextCompat.getColor(requireContext(), colorResId)
        return color
    }
}