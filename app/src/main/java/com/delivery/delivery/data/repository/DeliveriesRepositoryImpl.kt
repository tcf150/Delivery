package com.delivery.delivery.data.repository

import com.delivery.delivery.data.api.DeliveriesApi
import com.delivery.delivery.model.Deliveries

class DeliveriesRepositoryImpl(val deliveriesApi: DeliveriesApi) : DeliveriesRepository {

    override fun getDeliveries(offset: Int, limit: Int): List<Deliveries> {
        return emptyList()
    }

}