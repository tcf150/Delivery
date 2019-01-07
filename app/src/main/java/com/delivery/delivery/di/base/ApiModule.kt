package com.delivery.delivery.di.base

import com.delivery.delivery.data.api.DeliveriesApi
import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.data.repository.DeliveriesRepositoryImpl
import com.delivery.delivery.data.room.DeliveriesDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideDeliveriesApi(retrofit: Retrofit): DeliveriesApi {
        return retrofit.create(DeliveriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeliveriesRepository(deliveriesApi: DeliveriesApi, deliveriesDatabase: DeliveriesDatabase): DeliveriesRepository {
        return DeliveriesRepositoryImpl(deliveriesApi, deliveriesDatabase)
    }
}