package com.delivery.delivery.unit.test

import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


class RxJavaRule : TestRule {
    private var rxJavaHooker: RxJavaHooker = RxJavaHooker()

    fun getTestScheduler(): TestScheduler {
        return rxJavaHooker.testScheduler
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                rxJavaHooker.reset()
                rxJavaHooker.hookSchedulers()
                rxJavaHooker.hookMainThreadScheduler()
            }
        }
    }
}
