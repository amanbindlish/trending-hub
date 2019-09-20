package com.bindlish.trendinghub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bindlish.trendinghub.viewmodel.TrendingViewModel
import com.bindlish.trendinghub.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    protected abstract fun bindTrendingViewModel(trendingViewModel: TrendingViewModel): ViewModel
}