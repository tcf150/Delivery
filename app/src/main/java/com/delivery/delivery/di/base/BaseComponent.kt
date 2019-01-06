package com.delivery.delivery.di.base

import com.delivery.delivery.base.BaseApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class, ApiModule::class, NetworkModule::class])
interface BaseComponent {
    fun inject(baseApplication: BaseApplication)
}