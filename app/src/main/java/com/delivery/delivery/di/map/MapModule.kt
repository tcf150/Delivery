package com.delivery.delivery.di.map

import com.delivery.delivery.di.base.ActivityScope
import com.delivery.delivery.ui.map.MapContract
import com.delivery.delivery.ui.map.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class MapModule {
    @Provides
    @ActivityScope
    fun provideMapPresenter(): MapContract.Presenter = MapPresenter()
}