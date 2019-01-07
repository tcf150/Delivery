package com.delivery.delivery.ui.map

import com.delivery.delivery.base.BasePresenter
import com.delivery.delivery.model.Deliveries

class MapPresenter :
    BasePresenter<MapContract.View>(),
    MapContract.Presenter {

    private var deliveries: Deliveries? = null

    override fun init(deliveries: Deliveries) {
        this.deliveries = deliveries
    }

    override fun onMapReady() {
        deliveries?.run {
            view?.setMarker(this)
        }
    }

    override fun onLoaded() {
        deliveries?.run {
            view?.setDeliveriesTitle(description)
            view?.setDeliveriesPhoto(imageUrl)
        }
    }

}