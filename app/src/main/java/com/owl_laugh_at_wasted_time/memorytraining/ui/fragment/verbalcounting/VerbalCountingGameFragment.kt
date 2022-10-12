package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentVerbalCountingGameBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class VerbalCountingGameFragment : Fragment(R.layout.fragment_verbal_counting_game) {
    private val binding by viewBinding(FragmentVerbalCountingGameBinding::bind)

    private lateinit var level: Level

    override fun onAttach(context: Context) {
        super.onAttach(context)
        level = requireArguments().getParcelable<Level>("LEVEL") as Level
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}