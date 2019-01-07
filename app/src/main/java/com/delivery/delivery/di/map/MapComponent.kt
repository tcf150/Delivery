package com.delivery.delivery.di.map

import com.delivery.delivery.base.BaseApplication
import com.delivery.delivery.di.base.ActivityScope
import com.delivery.delivery.ui.map.MapActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MapModule::class])
interface MapComponent {
    fun inject(mapActivity: MapActivity)

    companion object {
        fun init(): MapComponent {
            return BaseApplication.app.component.plus(MapModule())
        }
    }
}