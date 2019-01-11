package com.delivery.delivery.base

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.delivery.delivery.di.base.BaseComponent
import com.delivery.delivery.di.base.BaseModule
import com.delivery.delivery.di.base.DaggerBaseComponent
import com.delivery.delivery.util.NetworkManager

class BaseApplication : Application() {
    lateinit var component: BaseComponent

    companion object {
        lateinit var app: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        component = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        component.inject(this)
        NetworkManager.init(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
}