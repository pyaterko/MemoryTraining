package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentChooseLevelBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding


class ChooseLevelFragment : Fragment(R.layout.fragment_choose_level) {
    private val binding by viewBinding(FragmentChooseLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonLevelEasy.setOnClickListener {
              launchVerbalCountingGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener {
                launchVerbalCountingGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener {
                launchVerbalCountingGameFragment(Level.HARD)
            }
        }
    }

    private fun launchVerbalCountingGameFragment(level: Level) {
        findNavController().navigate(
            R.id.action_chooseLevelFragment_to_verbalCountingGameFragment,
            bundleOf("LEVEL" to level)
        )
    }


}