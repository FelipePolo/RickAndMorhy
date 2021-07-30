package com.felipepolo.pruebaenvivo.di

import androidx.lifecycle.ViewModelProvider
import com.felipepolo.pruebaenvivo.utils.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
interface ViewModelModule {

    @Binds
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}