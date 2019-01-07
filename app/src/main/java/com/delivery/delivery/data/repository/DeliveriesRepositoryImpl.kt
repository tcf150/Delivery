package com.delivery.delivery.data.repository

import com.delivery.delivery.data.api.DeliveriesApi
import com.delivery.delivery.model.Deliveries
import rx.Observable

class DeliveriesRepositoryImpl(private val deliveriesApi: DeliveriesApi) : DeliveriesRepository {

    override fun getDeliveries(offset: Int, limit: Int): Observable<ArrayList<Deliveries>> = deliveriesApi.getDeliveries(offset, limit)

}