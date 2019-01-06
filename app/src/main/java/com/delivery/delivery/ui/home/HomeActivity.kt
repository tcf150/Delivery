package com.delivery.delivery.ui.home

import com.delivery.delivery.R
import com.delivery.delivery.base.BaseActivity
import com.delivery.delivery.model.Deliveries

class HomeActivity : BaseActivity(), HomeContract.View {

    lateinit var presenter: HomeContract.Presenter

    override fun contentView() = R.layout.activity_home

    override fun initPresenter() {
        presenter = HomePresenter()
        presenter.attachView(this)
    }

    override fun showDeliveriesList(deliveries: List<Deliveries>) {

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
