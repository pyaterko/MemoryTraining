package com.owl_laugh_at_wasted_time.memorytraining

import android.content.Context
import com.owl_laugh_at_wasted_time.memorytraining.di.AppComponent
import com.owl_laugh_at_wasted_time.memorytraining.di.DaggerAppComponent


object Initializer {
    fun component(context: Context): AppComponent {
        return DaggerAppComponent.factory().create(context)
    }
}