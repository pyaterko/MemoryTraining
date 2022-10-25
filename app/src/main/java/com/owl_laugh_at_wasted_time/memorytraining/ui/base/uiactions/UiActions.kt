package com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions

import android.content.Context

interface UiActions {
    fun getString(messageRes: Int): String
    fun getContext(): Context
}