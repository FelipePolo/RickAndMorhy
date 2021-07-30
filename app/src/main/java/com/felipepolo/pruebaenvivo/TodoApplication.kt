package com.felipepolo.pruebaenvivo

import com.felipepolo.pruebaenvivo.di.DaggerAppGraph
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class TodoApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppGraph.factory().create(this)
    }
}