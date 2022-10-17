package com.owl_laugh_at_wasted_time.memorytraining.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.owl_laugh_at_wasted_time.memorytraining.ui.activity.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject


open class BaseFragment(layout: Int) : Fragment(layout) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

    fun launchScope(block: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block.invoke()
            }
        }
    }

}