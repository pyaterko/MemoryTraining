package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentDifficultyLevelBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class DifficultyLevelFragment:Fragment(R.layout.fragment_difficulty_level) {

    private val binding by viewBinding(FragmentDifficultyLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLevelEasy.setOnClickListener {
            findNavController().navigate(
              DifficultyLevelFragmentDirections
                  .actionDifficultyLevelFragmentToMemoryFragment(Level.EASY)
            )
        }
        binding.buttonLevelMedium.setOnClickListener {
            findNavController().navigate(
                DifficultyLevelFragmentDirections
                    .actionDifficultyLevelFragmentToMemoryFragment(Level.NORMAL)
            )
        }
        binding.buttonLevelHard.setOnClickListener {
            findNavController().navigate(
                DifficultyLevelFragmentDirections
                    .actionDifficultyLevelFragmentToMemoryFragment(Level.HARD)
            )

        }
    }
}