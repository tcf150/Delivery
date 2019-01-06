package com.delivery.delivery.di.base

import com.delivery.delivery.util.Constant
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(requestInterceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder().connectionSpecs(
            arrayListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        rxJavaCallAdapterFactory: RxJavaCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().client(client)
            .baseUrl(Constant.API_URL)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}