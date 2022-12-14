package com.owl_laugh_at_wasted_time.memorytraining.di

import android.content.Context
import com.owl_laugh_at_wasted_time.memorytraining.ui.activity.MainActivity
import com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.game.VerbalCountingGameFragment
import com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory.MemoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        DataModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]

)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun context(): Context
    fun inject(activity: MainActivity)
    fun inject(fragment: VerbalCountingGameFragment)
    fun inject(fragment: MemoryFragment)
}