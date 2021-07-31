package com.felipepolo.pruebaenvivo.ui.home.di

import androidx.lifecycle.ViewModel
import com.felipepolo.pruebaenvivo.di.ViewModelKey
import com.felipepolo.pruebaenvivo.domain.HomeRepoImp
import com.felipepolo.pruebaenvivo.domain.HomeRepositoryInt
import com.felipepolo.pruebaenvivo.ui.home.HomeVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    abstract fun bindsHomeViewModel(viewModel: HomeVM): ViewModel

    @Binds
    abstract fun bindsHomeRepository(homeRepoImp: HomeRepoImp): HomeRepositoryInt
}