package com.delivery.delivery.ui.home

import com.delivery.delivery.R
import com.delivery.delivery.base.BaseActivity
import com.delivery.delivery.di.home.HomeComponent
import com.delivery.delivery.model.Deliveries
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun contentView() = R.layout.activity_home

    override fun injectDependency() {
        val homeComponent = HomeComponent.init()
        homeComponent.inject(this)
    }

    override fun initPresenter() {
        presenter.attachView(this)
    }

    override fun showDeliveriesList(deliveries: List<Deliveries>) {

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
