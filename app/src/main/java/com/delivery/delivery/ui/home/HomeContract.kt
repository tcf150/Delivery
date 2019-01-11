package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BaseContract
import com.delivery.delivery.model.Deliveries

interface HomeContract {
    interface View : BaseContract.View {
        fun clearDeliveriesList()

        fun addDeliveriesList(deliveries: ArrayList<Deliveries>)

        fun launchMapView(deliveries: Deliveries)

        fun hideLoading()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onDeliveriesClicked(deliveries : Deliveries)

        fun loadMoreDeliveries(itemSize: Int, refresh: Boolean = false)
    }
}