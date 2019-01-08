package com.delivery.delivery.unit.test

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import rx.schedulers.TestScheduler

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
                base.evaluate()
            }
        }
    }
}
