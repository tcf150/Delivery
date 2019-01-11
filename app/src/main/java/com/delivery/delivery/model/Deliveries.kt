package com.delivery.delivery.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delivery.delivery.data.room.DeliveriesDatabase
import kotlinx.android.parcel.Parcelize

@Entity(tableName = DeliveriesDatabase.TABLE_DELIVERIES)
@Parcelize
data class Deliveries(
    @PrimaryKey val id: Long,
    val description: String,
    val imageUrl: String,
    @Embedded val location: Location
) : Parcelable