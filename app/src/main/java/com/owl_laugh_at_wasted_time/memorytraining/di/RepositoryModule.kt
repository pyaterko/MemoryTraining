package com.owl_laugh_at_wasted_time.memorytraining.di

import com.owl_laugh_at_wasted_time.memorytraining.data.GameVerbalCountingRepositoryImpl
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.GameVerbalCountingRepository
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions.AndroidUiActions
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions.UiActions
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindGameVerbalCountingRepository(
        impl: GameVerbalCountingRepositoryImpl
    ): GameVerbalCountingRepository

    @Singleton
    @Binds
    fun bindUiActions(impl: AndroidUiActions): UiActions

}