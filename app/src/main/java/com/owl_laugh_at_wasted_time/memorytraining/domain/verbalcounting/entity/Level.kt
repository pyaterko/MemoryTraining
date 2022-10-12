package com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level:Parcelable {
    EASY, NORMAL, HARD
}