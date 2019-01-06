package com.delivery.delivery.base

import android.app.Application
import com.delivery.delivery.di.base.BaseComponent
import com.delivery.delivery.di.base.BaseModule
import com.delivery.delivery.di.base.DaggerBaseComponent

class BaseApplication : Application() {
    lateinit var component: BaseComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        component.inject(this)
    }
}