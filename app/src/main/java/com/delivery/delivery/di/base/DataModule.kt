package com.delivery.delivery.di.base

import androidx.room.Room
import com.delivery.delivery.base.BaseApplication
import com.delivery.delivery.data.room.DeliveriesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDeliveriesDatabase(app: BaseApplication): DeliveriesDatabase {
        return Room.databaseBuilder(
            app, DeliveriesDatabase::class.java,
            DeliveriesDatabase.DATABASE_NAME
        ).build()
    }
}