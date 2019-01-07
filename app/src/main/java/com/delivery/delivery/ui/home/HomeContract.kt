package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BaseContract
import com.delivery.delivery.model.Deliveries

interface HomeContract {
    interface View : BaseContract.View {
        fun addDeliveriesList(deliveries: ArrayList<Deliveries>)

        fun launchMapView(deliveries: Deliveries)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onDeliveriesClicked(deliveries : Deliveries)

        fun loadMoreDeliveries(itemSize: Int)
    }
}