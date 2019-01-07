package com.delivery.delivery.data.room

import androidx.room.TypeConverter
import com.delivery.delivery.base.BaseApplication
import com.delivery.delivery.model.Location

class DeliveriesConverter {
    private val gson = BaseApplication.app.gson

    @TypeConverter
    fun toUserJson(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun fromUserJson(location: String): Location {
        return gson.fromJson(location, Location::class.java)
    }
}