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
        private const val MIN_SUM_VALUE = 2
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
            questionAdd(maxValue, countOfOptions)
        }
        Operation.SUBTRACTION -> {
            questionSub(maxValue, countOfOptions)
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
        countOfOptions: Int
    ): Question {
        val firstNumber = Random.nextInt(MIN_ANSWER_VALUE, maxValue / 2)
        val secondNumber = Random.nextInt(MIN_ANSWER_VALUE, maxValue / 2)
        val result = firstNumber + secondNumber
        val opcions = HashSet<Int>()
        opcions.add(result)
        val from = result - 6
        val to = result + 6
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

    private fun questionSub(
        maxValue: Int,
        countOfOptions: Int
    ): Question {
        val firstNumber = Random.nextInt(MIN_ANSWER_VALUE, maxValue + 1)
        val secondNumber = Random.nextInt(MIN_ANSWER_VALUE, firstNumber )
        val opcions = HashSet<Int>()
        val result = firstNumber - secondNumber
        opcions.add(result)
        val from = result - 6
        val to = result + 6
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
        val from = result - 10
        val to = result + 20
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

    private fun questionDiv(
        maxValue: Int,
        countOfOptions: Int,
        level: Level
    ): Question {
        val secondNumber = secondNumberInDiv(maxValue, level)
        val result = resultInDiv(maxValue, level)
        val opcions = HashSet<Int>()
        val firstNumber = result * secondNumber
        opcions.add(result)
        val from = result - 10
        val to = result + 20
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

    private fun resultInDiv(maxValue: Int, level: Level): Int =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE, MAX_MULTIPLICATION * 3)
            }
            Level.NORMAL -> {
                Random.nextInt(MAX_MULTIPLICATION, maxValue / 10 + 1)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION, (maxValue / 20) + 1)
            }
        }

    private fun secondNumberInDiv(maxValue: Int, level: Level): Int =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_ANSWER_VALUE, MAX_MULTIPLICATION)
            }
            Level.NORMAL -> {
                Random.nextInt(MAX_MULTIPLICATION, MAX_MULTIPLICATION * 2 + 1)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION, (maxValue / 20) + 1)
            }
        }


    private fun firstNumberInMul(maxValue: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_SUM_VALUE, MAX_MULTIPLICATION)
            }
            Level.NORMAL -> {
                Random.nextInt(MAX_MULTIPLICATION, maxValue / 10 + 1)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION, maxValue / 20 + 1)
            }
        }

    private fun secondNumberInMul(maxValue: Int, level: Level) =
        when (level) {
            Level.EASY -> {
                Random.nextInt(MIN_SUM_VALUE, MAX_MULTIPLICATION)
            }
            Level.NORMAL -> {
                Random.nextInt(MIN_SUM_VALUE, MAX_MULTIPLICATION)
            }
            Level.HARD -> {
                Random.nextInt(MAX_MULTIPLICATION, (maxValue / 20) + 1)
            }
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
                    1000, 10, 90, 60
                )
            }
            Level.HARD -> {
                GameSettings(
                    2000, 10, 90, 60
                )
            }
        }
    }
}