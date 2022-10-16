package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.choose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentChooseLevelBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding


class ChooseLevelFragment : Fragment(R.layout.fragment_choose_level) {
    private val binding by viewBinding(FragmentChooseLevelBinding::bind)

    val args by navArgs<ChooseLevelFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelEasy.setOnClickListener {
                launchVerbalCountingGameFragment(Level.EASY, args.operation)
            }
            buttonLevelNormal.setOnClickListener {
                launchVerbalCountingGameFragment(Level.NORMAL, args.operation)
            }
            buttonLevelHard.setOnClickListener {
                launchVerbalCountingGameFragment(Level.HARD, args.operation)
            }
        }
    }

    private fun launchVerbalCountingGameFragment(level: Level, operation: Operation) {
        findNavController().navigate(
            ChooseLevelFragmentDirections
                .actionChooseLevelFragmentToVerbalCountingGameFragment(level, operation)
        )
    }


}