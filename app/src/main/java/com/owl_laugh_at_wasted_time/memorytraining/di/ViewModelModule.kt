package com.owl_laugh_at_wasted_time.memorytraining.di

import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.verbalcounting.game.VerbalCountingGameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VerbalCountingGameViewModel::class)
    fun bindShoppingListViewModel(viewModel: VerbalCountingGameViewModel): ViewModel


}