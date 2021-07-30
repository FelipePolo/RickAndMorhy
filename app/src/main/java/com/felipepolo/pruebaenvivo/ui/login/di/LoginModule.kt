package com.felipepolo.pruebaenvivo.ui.login.di

import androidx.lifecycle.ViewModel
import com.felipepolo.pruebaenvivo.di.ViewModelKey
import com.felipepolo.pruebaenvivo.ui.login.LoginVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = [])
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginVM::class)
    abstract fun bindsLoginViewModel(viewModel: LoginVM): ViewModel

    /*
    @Binds
    abstract fun provideRepo(exampleRepoImp: ExampleRepoImp):ExampleRepoInterface
     */


}