package com.felipepolo.pruebaenvivo.di

import android.app.Application
import com.felipepolo.pruebaenvivo.TodoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class
    ]
)

@Singleton
interface AppGraph : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppGraph
    }
}