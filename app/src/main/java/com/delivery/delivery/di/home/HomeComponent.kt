package com.delivery.delivery.di.home

import com.delivery.delivery.base.BaseApplication
import com.delivery.delivery.di.base.ActivityScope
import com.delivery.delivery.ui.home.HomeActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)

    companion object {
        fun init(): HomeComponent {
            return BaseApplication.app.component.plus(HomeModule())
        }
    }
}