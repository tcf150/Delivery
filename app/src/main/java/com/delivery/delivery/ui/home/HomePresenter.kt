package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BasePresenter
import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.model.Deliveries

class HomePresenter(val deliveriesRepository: DeliveriesRepository) : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun onLoaded() {

    }

    override fun onDeliveriesClicked(deliveries: Deliveries) {

    }

}