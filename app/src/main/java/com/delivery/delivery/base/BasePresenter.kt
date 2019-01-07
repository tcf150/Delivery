package com.delivery.delivery.base

abstract class BasePresenter<V> : BaseContract.Presenter<V> {

    protected var view : V? = null

    abstract fun onLoaded()

    override fun attachView(view: V) {
        this.view = view
        onLoaded()
    }

    override fun detachView() {
        this.view = null
    }

}