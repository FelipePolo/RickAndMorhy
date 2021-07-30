package com.felipepolo.pruebaenvivo.di

import com.felipepolo.pruebaenvivo.di.scope.FragmentScope
import com.felipepolo.pruebaenvivo.ui.MainActivity
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

}