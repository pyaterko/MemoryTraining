package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.finish

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentGameFinishedBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameResult
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding
import java.text.SimpleDateFormat
import java.util.*


class GameFinishedFragment : BaseFragment(R.layout.fragment_game_finished) {

    private val binding by viewBinding(FragmentGameFinishedBinding::bind)

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val gameResult: GameResult by lazy { args.resuit }
    private lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())

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
            findNavController().navigate(
                GameFinishedFragmentDirections
                    .actionGameFinishedFragmentToVerbalCountingGameFragment()
            )
        }
        setStatisticsCounter()
    }

    private fun setStatisticsCounter() {
        if (gameResult.winner) {
            if (sharedPreferences.getBoolean(getString(R.string.game_options), true)) {
                if (gameResult.countOfRightAnswers >= getStatisticsCounter()) {
                    requireContext().getSharedPreferences(
                        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
                    ).edit().putInt(COUNTING, gameResult.countOfRightAnswers).apply()
                    requireContext().getSharedPreferences(
                        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
                    ).edit().putString(CURRENT_STATISTICS_COUNTING, getStringStatistics()).apply()
                }
            } else {
                if (gameResult.countOfRightAnswers >= getStatisticsCounterEndurance()) {
                    requireContext().getSharedPreferences(
                        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
                    ).edit().putInt(COUNTING_ENDURANCE, gameResult.countOfRightAnswers).apply()
                    requireContext().getSharedPreferences(
                        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
                    ).edit().putString(
                        CURRENT_STATISTICS_COUNTING_ENDURANCE,
                        getStringStatisticsEndurance()
                    ).apply()
                }

            }

        }
    }

    private fun getStatisticsCounter() = requireContext().getSharedPreferences(
        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
    ).getInt(COUNTING, 0)

    private fun getStatisticsCounterEndurance() = requireContext().getSharedPreferences(
        CURRENT_STATISTICS_COUNTING, Context.MODE_PRIVATE
    ).getInt(COUNTING_ENDURANCE, 0)

    private fun getStringStatistics(): String {
        return "Лучший результат устного счета\nна время:\t\t$dateOfCreation\n${getOperationByString()} :\nответов :\t\t ${gameResult.countOfQuestions}\nправильных ответов : ${gameResult.countOfRightAnswers} "
    }

    private fun getStringStatisticsEndurance(): String {
        return "Лучший результат устного счета\nна выносливость:\t\t$dateOfCreation\n${getOperationByString()} :\nответов :\t\t ${gameResult.countOfQuestions}\nправильных ответов : ${gameResult.countOfRightAnswers} "
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

    fun getOperationByString(): String {
        val level =
            sharedPreferences.getString(getString(R.string.arithmetic_operation_key), "ADDITION")
                ?: "ADDITION"
        return when (level) {
            "ADDITION" -> {
                getString(R.string.addition)
            }
            "SUBTRACTION" -> {
                getString(R.string.subtraction)
            }
            "MULTIPLICATION" -> {
                getString(R.string.multiplication)
            }
            "DIVISION" -> {
                getString(R.string.division)
            }
            else -> {
                getString(R.string.addition)
            }
        }
    }

}



