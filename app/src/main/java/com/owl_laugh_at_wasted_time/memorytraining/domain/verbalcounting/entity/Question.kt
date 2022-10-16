package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val result:Int,
    val firstNumber:Int,
    val secondNumber:Int,
    val options:List<Int>
):Parcelable
