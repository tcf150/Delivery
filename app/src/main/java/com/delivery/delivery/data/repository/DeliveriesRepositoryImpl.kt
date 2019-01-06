package com.delivery.delivery.data.repository

import com.delivery.delivery.data.api.DeliveriesApi
import com.delivery.delivery.model.Deliveries
import rx.Observable

class DeliveriesRepositoryImpl(val deliveriesApi: DeliveriesApi) : DeliveriesRepository {

    override fun getDeliveries(offset: Int, limit: Int): Observable<List<Deliveries>> = deliveriesApi.getDeliveries(offset, limit)

}