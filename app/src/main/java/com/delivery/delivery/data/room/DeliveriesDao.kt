package com.delivery.delivery.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery.delivery.model.Deliveries

@Dao
interface DeliveriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDeliveriesList(deliveriesList: List<Deliveries>): List<Long>

    @Query("SELECT * FROM deliveries LIMIT :offset, :limit")
    fun getDeliveriesList(offset: Int, limit: Int): List<Deliveries>

    @Query("DELETE FROM deliveries")
    fun deleteAllInboxList()
}