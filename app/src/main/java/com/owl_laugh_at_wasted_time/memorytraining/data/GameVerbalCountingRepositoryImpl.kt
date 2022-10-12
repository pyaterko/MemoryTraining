package com.owl_laugh_at_wasted_time.memorytraining.data

import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.GameSettings
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Question
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameVerbalCountingRepository
import kotlin.random.Random

object GameVerbalCountingRepositoryImpl : GameVerbalCountingRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generatyQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val firstTerm = Random.nextInt(MIN_ANSWER_VALUE, maxSumValue + 1)
        val opcions = HashSet<Int>()
        val secondTerm = sum - firstTerm
        opcions.add(secondTerm)
        val from = maxSumValue - 6
        val to = maxSumValue + 6
        while (opcions.size < countOfOptions) {
            opcions.add(Random.nextInt(from, to))

        }
        return Question(sum, firstTerm, opcions.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
      return when(level){
          Level.EASY->{
              GameSettings(
                  10,3,60,8
              )
          }
          Level.NORMAL->{
              GameSettings(
                  50,20,80,60
              )
          }
          Level.HARD->{
              GameSettings(
                  100,20,90,30
              )
          }
      }
    }
}