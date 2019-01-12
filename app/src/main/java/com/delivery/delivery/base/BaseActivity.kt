package com.delivery.delivery.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(),
    BaseContract.View {

    @LayoutRes
    abstract fun contentView(): Int

    abstract fun initPresenter(savedInstanceState: Bundle?)

    abstract fun injectDependency()

    abstract fun setupView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        setContentView(contentView())
        setupView()
        initPresenter(savedInstanceState)
    }
}