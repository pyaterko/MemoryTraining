package com.owl_laugh_at_wasted_time.memorytraining.ui.fragment.verbalcounting

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameResult
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GenerateQuestionUsecase
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GetGameSettingsUsecase
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions.UiActions
import javax.inject.Inject

class VerbalCountingGameViewModel @Inject constructor(
    private val generateQuestion: GenerateQuestionUsecase,
    private val getGameSettings: GetGameSettingsUsecase,
    private val uiActions: UiActions
) : ViewModel() {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    var timer: CountDownTimer? = null

    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    private val _timeLiveData = MutableLiveData<String>()
    val timeLiveData: LiveData<String> = _timeLiveData

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> = _question

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int> = _percentOfRightAnswers

    private val _colorTimer = MutableLiveData<Int>()
    val colorTimer: LiveData<Int> = _colorTimer

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String> = _progressAnswers

    private val _enoughCorrectAnswers = MutableLiveData<Boolean>()
    val enoughCorrectAnswers: LiveData<Boolean> = _enoughCorrectAnswers

    private val _enoughPercentCorrectAnswers = MutableLiveData<Boolean>()
    val enoughPercentCorrectAnswers: LiveData<Boolean> = _enoughPercentCorrectAnswers

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int> = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> = _gameResult

    fun startGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettings(level)
        _minPercent.value = gameSettings.minPersentOfRightAnswers
        startTimer()
        generetQuestion()
        updateProgress()
    }

     fun chooeAnswee(number: Int) {
        if (number == question.value?.result) {
            countOfRightAnswers++
        }
        countOfQuestions++
        updateProgress()
        generetQuestion()
    }

    private fun updateProgress() {
        val percent = ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        _percentOfRightAnswers.value = if (countOfQuestions==0) 0 else percent
        _progressAnswers.value = String.format(
            uiActions.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        _enoughCorrectAnswers.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercentCorrectAnswers.value = percent >= gameSettings.minPersentOfRightAnswers
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(timeUntilCompletion: Long) {
                _colorTimer.value=(timeUntilCompletion / MILLIS_IN_SECONDS).toInt()
                val time = formatTime(timeUntilCompletion)
                _timeLiveData.value = time
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun formatTime(timeUntilCompletion: Long): String {
        val seconds = timeUntilCompletion / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val remainingSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    private fun generetQuestion() {
        _question.value = generateQuestion(gameSettings.maxSumValue)
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughCorrectAnswers.value == true && enoughPercentCorrectAnswers.value == true,
            countOfRightAnswers,
            countOfQuestions,
            gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }
}