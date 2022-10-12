package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPersentOfRightAnswers: Int,
    val gameTimaInSeconds: Int
) : Parcelable {

}
