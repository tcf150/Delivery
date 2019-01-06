package com.delivery.delivery.di.base

import com.delivery.delivery.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule(val app: BaseApplication) {
    @Provides
    @Singleton
    fun provideApplication(): BaseApplication {
        return app
    }
}