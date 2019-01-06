package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BasePresenter
import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.model.Deliveries
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomePresenter(val deliveriesRepository: DeliveriesRepository) : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun onLoaded() {
        deliveriesRepository.getDeliveries(0, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({deliveriesList ->
                view?.showDeliveriesList(deliveriesList)
            }, {it.printStackTrace()})
    }

    override fun onDeliveriesClicked(deliveries: Deliveries) {

    }

}