package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.choose

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentArithmeticOperationBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class ArithmeticOperationFragment : Fragment(R.layout.fragment_arithmetic_operation) {

    private val binding by viewBinding(FragmentArithmeticOperationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBarMenu()
        with(binding) {
            addition.setOnClickListener {
                findNavController().navigate(
                    ArithmeticOperationFragmentDirections
                        .actionArithmeticOperationFragmentToChooseLevelFragment(Operation.ADDITION)
                )
            }
            subtraction.setOnClickListener {
                findNavController().navigate(
                    ArithmeticOperationFragmentDirections
                        .actionArithmeticOperationFragmentToChooseLevelFragment(Operation.SUBTRACTION)
                )
            }
            multiplication.setOnClickListener {
                findNavController().navigate(
                    ArithmeticOperationFragmentDirections
                        .actionArithmeticOperationFragmentToChooseLevelFragment(Operation.MULTIPLICATION)
                )
            }
            division.setOnClickListener {
                findNavController().navigate(
                    ArithmeticOperationFragmentDirections
                        .actionArithmeticOperationFragmentToChooseLevelFragment(Operation.DIVISION)
                )
            }
        }

    }

    private fun setToolBarMenu() {
        val menuHost: MenuHost = activity as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.math_operation_menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.tb_multiplication -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToMultiplicatonTableFragment()
                        )
                    }
                    R.id.tb_division -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToDivisionTableFragment()
                        )
                    }
                    R.id.help_addition -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToAdditionPageFragment()
                        )
                    }
                    R.id.help_subtraction -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToSubtractionPageFragment()
                        )
                    }
                    R.id.help_multiplication -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToMultiplicatonPageFragment()
                        )
                    }
                    R.id.help_division -> {
                        findNavController().navigate(
                            ArithmeticOperationFragmentDirections
                                .actionArithmeticOperationFragmentToDivisionPageFragment()
                        )
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}