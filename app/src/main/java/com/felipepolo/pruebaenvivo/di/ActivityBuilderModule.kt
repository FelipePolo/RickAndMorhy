package com.felipepolo.pruebaenvivo.di

import com.felipepolo.pruebaenvivo.di.scope.ActivityScope
import com.felipepolo.pruebaenvivo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract  fun bindsMainActivity(): MainActivity

}