package com.delivery.delivery.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val lat: Double,
    val lng: Double,
    val address: String
) : Parcelable