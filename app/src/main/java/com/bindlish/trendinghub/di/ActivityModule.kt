package com.bindlish.trendinghub.di

import com.bindlish.trendinghub.ui.TrendingActivity
import com.bindlish.trendinghub.ui.TrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeTrendingActivity(): TrendingActivity

    @ContributesAndroidInjector
    internal abstract fun contributeTrendingFragment(): TrendingFragment
}