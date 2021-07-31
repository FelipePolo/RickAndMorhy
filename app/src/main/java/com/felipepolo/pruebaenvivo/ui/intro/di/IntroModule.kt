package com.felipepolo.pruebaenvivo.ui.intro.di

import androidx.lifecycle.ViewModel
import com.felipepolo.pruebaenvivo.di.ViewModelKey
import com.felipepolo.pruebaenvivo.ui.intro.IntroVM
import com.felipepolo.pruebaenvivo.ui.login.LoginVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class IntroModule {

    @Binds
    @IntoMap
    @ViewModelKey(IntroVM::class)
    abstract fun bindsIntroViewModel(viewModel: IntroVM): ViewModel

}