package com.delivery.delivery.di.base

import com.delivery.delivery.base.BaseApplication
import com.delivery.delivery.di.home.HomeComponent
import com.delivery.delivery.di.home.HomeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class, NetworkModule::class, ApiModule::class, DataModule::class])
interface BaseComponent {
    fun inject(baseApplication: BaseApplication)

    fun plus(homeModule: HomeModule): HomeComponent
}