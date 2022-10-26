package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.startscreen

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentStartScreenBinding
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class StartScreenFragment : BaseFragment(R.layout.fragment_start_screen) {

    private val binding by viewBinding(FragmentStartScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setToolBarMenu()
        val statistics = requireContext().getSharedPreferences(
            CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
        )
            .getString(
                CURRENT_STATISTICS_COUNTING, ""
            )
        binding.statisticsCounting.text = statistics
        val statisticsEndurance = requireContext().getSharedPreferences(
            CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
        )
            .getString(
                CURRENT_STATISTICS_COUNTING_ENDURANCE, ""
            )
        binding.statisticsCountingEndurance.text = statisticsEndurance
        val statisticsMemory = requireContext().getSharedPreferences(
            CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
        )
            .getString(
                STATISTIC_COUNTER_MEMORY, ""
            )
        binding.statisticsMemory.text = statisticsMemory

    }

    private fun setListeners() {
        binding.buttonVerbal.setOnClickListener {
            launchFragment(
                StartScreenFragmentDirections
                    .actionStartScreenFragmentToVerbalCountingGameFragment()
            )
        }
        binding.buttonMemory.setOnClickListener {
            launchFragment(
                StartScreenFragmentDirections
                    .actionStartScreenFragmentToMemoryFragment()
            )
        }
    }

    private fun setToolBarMenu() {
        val menuHost: MenuHost = activity as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.settings_menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.settins -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToSettingsFragment()
                        )
                    }
                    R.id.tb_multiplication -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToMultiplicatonTableFragment()
                        )
                    }
                    R.id.tb_division -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToDivisionTableFragment()
                        )
                    }
                    R.id.help_addition -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToAdditionPageFragment()
                        )
                    }
                    R.id.help_subtraction -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToSubtractionPageFragment()
                        )
                    }
                    R.id.help_multiplication -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToMultiplicatonPageFragment()
                        )
                    }
                    R.id.help_division -> {
                        launchFragment(
                            StartScreenFragmentDirections
                                .actionStartScreenFragmentToDivisionPageFragment()
                        )
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun launchFragment(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}