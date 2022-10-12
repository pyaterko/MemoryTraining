package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentGameFinishedBinding
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding


class GameFinishedFragment : Fragment(R.layout.fragment_game_finished) {
private val binding by viewBinding(FragmentGameFinishedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}