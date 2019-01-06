package com.delivery.delivery.data.repository

import com.delivery.delivery.model.Deliveries

interface DeliveriesRepository {
    fun getDeliveries(offset: Int, limit: Int): List<Deliveries>
}