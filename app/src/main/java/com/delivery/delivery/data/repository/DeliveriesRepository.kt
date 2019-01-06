package com.delivery.delivery.data.repository

import com.delivery.delivery.model.Deliveries
import rx.Observable

interface DeliveriesRepository {
    fun getDeliveries(offset: Int, limit: Int): Observable<List<Deliveries>>
}