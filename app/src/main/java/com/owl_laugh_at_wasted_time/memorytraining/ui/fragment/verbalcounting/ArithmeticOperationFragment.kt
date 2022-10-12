package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentArithmeticOperationBinding
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class ArithmeticOperationFragment : Fragment(R.layout.fragment_arithmetic_operation) {

    private val binding by viewBinding(FragmentArithmeticOperationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            addition.setOnClickListener {
                findNavController().navigate(R.id.action_arithmeticOperationFragment_to_chooseLevelFragment)
            }
            subtraction.setOnClickListener {
                findNavController().navigate(R.id.action_arithmeticOperationFragment_to_chooseLevelFragment)
            }
            multiplication.setOnClickListener {
                findNavController().navigate(R.id.action_arithmeticOperationFragment_to_chooseLevelFragment)
            }
            division.setOnClickListener {
                findNavController().navigate(R.id.action_arithmeticOperationFragment_to_chooseLevelFragment)
            }
        }

    }
}