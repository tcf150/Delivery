package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BasePresenter
import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.model.Deliveries
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomePresenter(private val deliveriesRepository: DeliveriesRepository) :
    BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {

    private var deliveriesSubscription: Subscription? = null

    override fun onLoaded() {
       loadDeliveries(0, false)
    }

    override fun loadMoreDeliveries(itemSize: Int, refresh: Boolean) {
        loadDeliveries(itemSize, refresh)
    }

    private fun loadDeliveries(itemSize: Int, refresh: Boolean) {
        if(deliveriesSubscription != null) return
        deliveriesSubscription = deliveriesRepository.getDeliveries(itemSize, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { deliveriesSubscription = null}
            .subscribe({deliveriesList ->
                view?.run {
                    if (refresh) clearDeliveriesList()
                    addDeliveriesList(deliveriesList)
                    hideLoading()
                }
            }, {it.printStackTrace()})
    }

    override fun onDeliveriesClicked(deliveries: Deliveries) {
        view?.launchMapView(deliveries)
    }

    override fun detachView() {
        super.detachView()
        deliveriesSubscription?.unsubscribe()
    }
}