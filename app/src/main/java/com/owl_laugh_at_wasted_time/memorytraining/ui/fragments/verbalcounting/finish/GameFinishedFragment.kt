package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.finish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentGameFinishedBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameResult
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding


class GameFinishedFragment : Fragment(R.layout.fragment_game_finished) {

    private val binding by viewBinding(FragmentGameFinishedBinding::bind)

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val  gameResult:GameResult by lazy { args.resuit }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emojiResult.setImageResource(getSmileResId())

        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.required_score),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            gameResult.countOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            gameResult.gameSettings.minPersentOfRightAnswers
        )
        binding.tvScorePercentage.text = String.format(
            getString(R.string.score_percentage),
            getPercentOfRightAnswers()
        )
        binding.buttonRetry.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId(): Int = if (gameResult.winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }

}