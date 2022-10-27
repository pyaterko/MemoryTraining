package com.owl_laugh_at_wasted_time.memorytraining.data

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameVerbalCountingRepository
import javax.inject.Inject
import kotlin.random.Random

class GameVerbalCountingRepositoryImpl @Inject constructor() : GameVerbalCountingRepository {

    companion object {
        private const val MIN_SUM_VALUE = 10
        private const val MIN_ANSWER_VALUE = 1
        private const val MAX_MULTIPLICATION = 10
    }

    override fun generatyQuestion(
        maxValue: Int,
        countOfOptions: Int,
        operation: Operation,
        level: Level
    ): Question = when (operation) {
        Operation.ADDITION -> {
            questionAdd(maxValue, countOfOptions, level)
        }
        Operation.SUBTRACTION -> {
            questionSub(maxValue, countOfOptions, level)
        }
        Operation.MULTIPLICATION -> {
            questionMul(maxValue, countOfOptions, level)
        }
        Operation.DIVISION -> {
            questionDiv(maxValue, countOfOptions, level)
        }
    }

    private fun questionAdd(
        maxValue: Int,
        countOfOptions: Int,
        level: Level
    ): Question {
        val result = Random.nextInt(MIN_SUM_VALUE, maxValue + 1)
        val secondNumber = secondNumberInAdd(result, level)
        val firstNumber = result - secondNumber
        val opcions = HashSet<Int>()
        opcions.add(result)
        val from = result - 4
        val to = result + 4
        while (opcions.size < countOfOptions) {
            val x = Random.nextInt(from, to)
            val number = if (x < 0) {
                Random.nextInt(1, 10)
            } else {
                x
            }
            opcions.add(number)
        }
        return Question(result, firstNumber, secondNumber, opcions.toList())
    }

    private fun secondNumberInAdd(result: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE, MIN_SUM_VALUE)
            }
            Level.NORMAL -> {
                Random.nextInt(MIN_SUM_VALUE, result)
            }
            Level.HARD -> {
                Random.nextInt(MIN_SUM_VALUE, result)
            }
        }

    private fun questionSub(
        maxValue: Int,
        countOfOptions: Int,
        level: Level
    ): Question {
        val firstNumber = Random.nextInt(MIN_SUM_VALUE + 1, maxValue + 1)
        val secondNumber = secondNumberInSub(firstNumber, level)
        val opcions = HashSet<Int>()
        val result = firstNumber - secondNumber
        opcions.add(result)
        val from = result - 4
        val to = result + 4
        while (opcions.size < countOfOptions) {
            val x = Random.nextInt(from, to)
            val number = if (x < 0) {
                Random.nextInt(1, 10)
            } else {
                x
            }
            opcions.add(number)
        }
        return Question(result, firstNumber, secondNumber, opcions.toList())
    }

    private fun secondNumberInSub(firstNumber: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE, MIN_SUM_VALUE)
            }
            Level.NORMAL -> {
                Random.nextInt(MIN_SUM_VALUE, firstNumber)
            }
            Level.HARD -> {
                Random.nextInt(MIN_SUM_VALUE, firstNumber)
            }
        }

    private fun questionMul(
        maxValue: Int,
        countOfOptions: Int,
        level: Level
    ): Question {
        val firstNumber = firstNumberInMul(maxValue, level)
        val secondNumber = secondNumberInMul(maxValue, level)
        val opcions = HashSet<Int>()
        val result = firstNumber * secondNumber
        opcions.add(result)
        val from = result - 4
        val to = result + 4
        while (opcions.size < countOfOptions) {
            val x = Random.nextInt(from, to)
            val number = if (x < 0) {
                Random.nextInt(1, 10)
            } else {
                x
            }
            opcions.add(number)
        }
        return Question(result, firstNumber, secondNumber, opcions.toList())
    }

    private fun firstNumberInMul(maxValue: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE + 1, MAX_MULTIPLICATION)
            }
            Level.NORMAL -> {
                Random.nextInt(MAX_MULTIPLICATION, maxValue + 1)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION, maxValue/10)
            }
        }

    private fun secondNumberInMul(maxValue: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE + 1, MAX_MULTIPLICATION)
            }
            Level.NORMAL -> {
                Random.nextInt(MIN_ANSWER_VALUE+1, MAX_MULTIPLICATION)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION+1, maxValue/10)
            }
        }

    private fun questionDiv(
        maxValue: Int,
        countOfOptions: Int,
        level: Level
    ): Question {
        val secondNumber = secondNumberInMul(maxValue, level)
        val result = firstNumberInMul(maxValue, level)
        val opcions = HashSet<Int>()
        val firstNumber = result * secondNumber
        opcions.add(result)
        val from = result - 4
        val to = result + 4
        while (opcions.size < countOfOptions) {
            val x = Random.nextInt(from, to)
            val number = if (x < 0) {
                Random.nextInt(1, 10)
            } else {
                x
            }
            opcions.add(number)
        }
        return Question(result, firstNumber, secondNumber, opcions.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.EASY -> {
                GameSettings(
                    100, 10, 90, 30
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    100, 3, 90, 60
                )
            }
            Level.HARD -> {
                GameSettings(
                    1000, 2, 90, 60
                )
            }
        }
    }
}