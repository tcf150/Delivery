package com.delivery.delivery.model

data class Deliveries(
    val id: Int,
    val description: String,
    val imageUrl: String,
    val location: Location
)