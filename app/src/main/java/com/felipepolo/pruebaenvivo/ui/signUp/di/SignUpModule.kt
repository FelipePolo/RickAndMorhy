package com.felipepolo.pruebaenvivo.ui.signUp.di

import androidx.lifecycle.ViewModel
import com.felipepolo.pruebaenvivo.di.ViewModelKey
import com.felipepolo.pruebaenvivo.ui.login.LoginVM
import com.felipepolo.pruebaenvivo.ui.signUp.SignUpVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignUpModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpVM::class)
    abstract fun bindsSignUpModel(viewModel: SignUpVM): ViewModel

}