package com.delivery.delivery.unit.test

import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaHooks
import rx.schedulers.Schedulers
import rx.schedulers.TestScheduler

class RxJavaHooker {
    val testScheduler = TestScheduler()

    init {
        RxJavaHooks.reset()
        RxAndroidPlugins.getInstance().reset()
    }

    fun hookSchedulers() {
        RxJavaHooks.setOnIOScheduler {  testScheduler }
        RxJavaHooks.setOnComputationScheduler { testScheduler }
        RxJavaHooks.setOnNewThreadScheduler { testScheduler }
    }

    fun hookMainThreadScheduler() {
        val rxAndroidPlugins = RxAndroidPlugins.getInstance()
        rxAndroidPlugins.registerSchedulersHook(object : RxAndroidSchedulersHook() {
            override fun getMainThreadScheduler(): Scheduler {
                return Schedulers.immediate()
            }
        })
    }

    fun reset() {
        RxJavaHooks.reset()
        RxAndroidPlugins.getInstance().reset()
    }
}