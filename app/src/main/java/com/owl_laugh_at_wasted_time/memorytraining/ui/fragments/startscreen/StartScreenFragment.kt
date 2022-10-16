package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.startscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentStartScreenBinding

class StartScreenFragment: Fragment(R.layout.fragment_start_screen) {

    private val binding by viewBinding ( FragmentStartScreenBinding::bind )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonVerbal.setOnClickListener {
            findNavController().navigate(R.id.action_startScreenFragment_to_arithmeticOperationFragment)
        }
        binding.buttonMemory.setOnClickListener {
            findNavController().navigate(R.id.action_startScreenFragment_to_difficultyLevelFragment)
        }
    }
}