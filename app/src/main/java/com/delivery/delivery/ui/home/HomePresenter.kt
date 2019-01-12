package com.delivery.delivery.ui.home

import com.delivery.delivery.base.BasePresenter
import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.model.Deliveries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val deliveriesRepository: DeliveriesRepository) :
    BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    private var deliveriesSubscription: Disposable? = null

    private var isEndOfList = false

    override fun init(deliveriesList: List<Deliveries>) {
       view?.run {
           addDeliveriesList(deliveriesList)
           hideLoading()
       }
    }

    override fun onLoaded() {

    }

    override fun loadMoreDeliveries(itemSize: Int, refresh: Boolean) {
        loadDeliveries(itemSize, refresh)
    }

    private fun loadDeliveries(itemSize: Int, refresh: Boolean) {
        if (refresh) isEndOfList = false
        if(deliveriesSubscription != null || isEndOfList) return
        deliveriesSubscription = deliveriesRepository.getDeliveries(itemSize, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (!refresh) view?.showLoading() }
            .doOnTerminate {
                view?.hideLoading()
                deliveriesSubscription = null
            }
            .subscribe({deliveriesList ->
                if (deliveriesList.size < 20) isEndOfList = true
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
        deliveriesSubscription?.dispose()
    }
}