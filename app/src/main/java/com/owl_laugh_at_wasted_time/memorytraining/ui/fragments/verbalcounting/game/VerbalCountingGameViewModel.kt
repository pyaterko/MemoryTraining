package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.game

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.*
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GenerateQuestionUsecase
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GetGameSettingsUsecase
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions.UiActions
import javax.inject.Inject

class VerbalCountingGameViewModel @Inject constructor(
    private val generateQuestion: GenerateQuestionUsecase,
    private val getGameSettings: GetGameSettingsUsecase,
    private val uiActions: UiActions
) : ViewModel() {

    var operation: Operation? = null


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

    private val _correctAnswer = MutableLiveData<Boolean>()
    val correctAnswer: LiveData<Boolean> = _correctAnswer

    fun startGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettings(level)
        _minPercent.value = gameSettings.minPersentOfRightAnswers
        startTimer()
        operation?.let { generetQuestion(it, level) }
        updateProgress()
    }

    fun chooeAnswee(number: Int, level: Level) {
        if (number == question.value?.result) {
            countOfRightAnswers++
        }
        countOfQuestions++
        updateProgress()
        operation?.let { generetQuestion(it, level) }
    }

    private fun updateProgress() {
        val percent = ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        _percentOfRightAnswers.value = if (countOfQuestions == 0) 0 else percent
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
                val sec = (timeUntilCompletion / MILLIS_IN_SECONDS).toInt()
                val player = MediaPlayer.create(uiActions.getContext(), R.raw.signal)
                if (sec < 11) {
                    player.start()
                }
                _colorTimer.value = sec
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

    private fun generetQuestion(operation: Operation, level: Level) {
        _question.value = generateQuestion(gameSettings.maxSumValue, operation, level)
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