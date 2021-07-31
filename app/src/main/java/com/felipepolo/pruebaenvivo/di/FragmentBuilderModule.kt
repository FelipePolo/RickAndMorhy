package com.felipepolo.pruebaenvivo.di

import com.felipepolo.pruebaenvivo.di.scope.FragmentScope
import com.felipepolo.pruebaenvivo.ui.MainActivity
import com.felipepolo.pruebaenvivo.ui.home.HomeFragment
import com.felipepolo.pruebaenvivo.ui.home.di.HomeModule
import com.felipepolo.pruebaenvivo.ui.intro.IntroFragment
import com.felipepolo.pruebaenvivo.ui.intro.di.IntroModule
import com.felipepolo.pruebaenvivo.ui.login.LoginFragment
import com.felipepolo.pruebaenvivo.ui.login.di.LoginModule
import com.felipepolo.pruebaenvivo.ui.signUp.SignUpFragment
import com.felipepolo.pruebaenvivo.ui.signUp.di.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract  fun bindsLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignUpModule::class])
    abstract  fun bindsSignUpFragment(): SignUpFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract  fun bindsHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [IntroModule::class])
    abstract fun bindsIntroFragment(): IntroFragment

}