package com.delivery.delivery.data.api

import com.delivery.delivery.model.Deliveries
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveriesApi {
    @GET("deliveries")
    fun getDeliveries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<List<Deliveries>>
}