package com.delivery.delivery.data.repository

import com.delivery.delivery.data.api.DeliveriesApi
import com.delivery.delivery.data.room.DeliveriesDatabase
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.util.NetworkManager
import io.reactivex.Observable

class DeliveriesRepositoryImpl(private val deliveriesApi: DeliveriesApi, private val deliveriesDatabase: DeliveriesDatabase) : DeliveriesRepository {

    override fun getDeliveries(offset: Int, limit: Int): Observable<List<Deliveries>> {
        return if (NetworkManager.get().isConnected()) {
           deliveriesApi.getDeliveries(offset, limit)
               .doOnNext { deliveriesDatabase.deliveriesDao().saveDeliveriesList(it) }
        } else {
            Observable.fromCallable {ArrayList(deliveriesDatabase.deliveriesDao().getDeliveriesList(offset, limit)) }
        }
    }

}