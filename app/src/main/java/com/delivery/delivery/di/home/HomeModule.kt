package com.delivery.delivery.di.home

import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.di.base.ActivityScope
import com.delivery.delivery.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    @ActivityScope
    fun provideHomePresenter(deliveriesRepository: DeliveriesRepository) = HomePresenter(deliveriesRepository)
}