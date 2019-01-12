package com.delivery.delivery.unit.test

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler


class RxJavaHooker {
    val testScheduler = TestScheduler()

    init {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    fun hookSchedulers() {
        RxJavaPlugins.onIoScheduler(testScheduler)
        RxJavaPlugins.onComputationScheduler(testScheduler)
        RxJavaPlugins.onNewThreadScheduler(testScheduler)
    }

    fun hookMainThreadScheduler() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    fun reset() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}