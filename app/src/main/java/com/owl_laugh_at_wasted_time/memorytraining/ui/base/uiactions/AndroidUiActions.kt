package com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions

import android.content.Context
import android.widget.Toast
import javax.inject.Inject


class AndroidUiActions @Inject constructor (
    private val application: Context
) : UiActions {

    override fun getString(messageRes:Int): String {
        return application.getString(messageRes)
    }

    override fun getContext()=application

}