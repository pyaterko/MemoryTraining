package com.owl_laugh_at_wasted_time.memorytraining.ui.base

import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.activity.MainActivity
import javax.inject.Inject


open class BaseFragment(layout: Int) : Fragment(layout) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

}