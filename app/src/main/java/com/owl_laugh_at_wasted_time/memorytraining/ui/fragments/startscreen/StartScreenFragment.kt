package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.startscreen

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
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentStartScreenBinding
import com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.choose.ArithmeticOperationFragmentDirections

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
        setToolBarMenu()
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
                    findNavController().navigate(StartScreenFragmentDirections
                        .actionStartScreenFragmentToSettingsFragment())
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}