package com.darshan.androidtutorial.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.androidtutorial.di.ViewModelKey
import com.darshan.androidtutorial.di.ViewModelProviderFactory
import com.darshan.androidtutorial.news.ui.NewListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewListViewModel::class)
    abstract fun newsListViewModel(viewModel: NewListViewModel): ViewModel
}