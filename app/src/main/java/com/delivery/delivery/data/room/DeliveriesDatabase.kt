package com.delivery.delivery.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delivery.delivery.data.room.DeliveriesDatabase.Companion.DATABASE_VERSION

import com.delivery.delivery.model.Deliveries

@Database(
    entities = [Deliveries::class],
    version = DATABASE_VERSION,
    exportSchema = true)
@TypeConverters(DeliveriesConverter::class)
abstract class DeliveriesDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "delivery.db"
        const val DATABASE_VERSION = 1

        const val TABLE_DELIVERIES = "deliveries"
    }

    abstract fun deliveriesDao(): DeliveriesDao
}