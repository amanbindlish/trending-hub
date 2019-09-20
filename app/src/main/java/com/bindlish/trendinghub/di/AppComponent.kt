package com.bindlish.trendinghub.di

import android.app.Application
import com.bindlish.trendinghub.TrendingHubApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Aman Bindlish on 20,September,2019
 */
@Component(
    modules = [ApiModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: TrendingHubApplication)
}