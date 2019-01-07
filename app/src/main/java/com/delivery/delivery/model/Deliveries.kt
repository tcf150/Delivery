package com.delivery.delivery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delivery.delivery.data.room.DeliveriesDatabase

@Entity(tableName = DeliveriesDatabase.TABLE_DELIVERIES)
data class Deliveries(
    @PrimaryKey val id: Long,
    val description: String,
    val imageUrl: String,
    val location: Location
)