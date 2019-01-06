package com.delivery.delivery.base

interface BaseContract {
    interface Presenter<in V> {
        fun attachView(view: V)

        fun detachView()
    }

    interface View
}