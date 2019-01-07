package com.delivery.delivery.ui.map

import com.delivery.delivery.base.BaseContract
import com.delivery.delivery.model.Deliveries

interface MapContract {
    interface View : BaseContract.View {
        fun setDeliveriesTitle(title: String)

        fun setDeliveriesPhoto(imageUrl: String)

        fun setMarker(deliveries: Deliveries)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun init(deliveries: Deliveries)

        fun onMapReady()
    }
}