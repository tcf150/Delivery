package com.delivery.delivery.data.room

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.model.Location
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DeliveriesDatabaseTest {
    private lateinit var deliveriesDao: DeliveriesDao
    private lateinit var db: DeliveriesDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(), DeliveriesDatabase::class.java).build()
        deliveriesDao = db.deliveriesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveDeliveries() {
        val deliveries = Deliveries(
            0,
            "Deliver documents to Andrio",
            "https://www.what-dog.net/Images/faces2/scroll0015.jpg",
            Location(22.336093, 114.155288, "Cheung Sha Wan")
            )
        val deliveries1 = Deliveries(
            1,
            "Deliver parcel to Leviero",
            "http://www.memoryandjustice.org/wp-content/uploads/2017/10/impossibly-cute-puppy-8.jpg",
            Location(22.319181, 114.170008, "Mong Kok")
        )
        deliveriesDao.saveDeliveriesList(listOf(deliveries, deliveries1))
        val deliveriesList = deliveriesDao.getDeliveriesList(0, 2)
        assertTrue(deliveriesList.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun getDeliveriesByOffsetLimit() {
        val deliveries = Deliveries(
            0,
            "Deliver documents to Andrio",
            "https://www.what-dog.net/Images/faces2/scroll0015.jpg",
            Location(22.336093, 114.155288, "Cheung Sha Wan")
        )
        val deliveries1 = Deliveries(
            1,
            "Deliver parcel to Leviero",
            "http://www.memoryandjustice.org/wp-content/uploads/2017/10/impossibly-cute-puppy-8.jpg",
            Location(22.319181, 114.170008, "Mong Kok")
        )
        deliveriesDao.saveDeliveriesList(listOf(deliveries, deliveries1))
        val deliveriesList = deliveriesDao.getDeliveriesList(1, 1)
        assertThat(deliveriesList[0], equalTo(deliveries1))
    }

    @Test
    fun clearDeliveries() {
        val deliveries = Deliveries(
            0,
            "Deliver documents to Andrio",
            "https://www.what-dog.net/Images/faces2/scroll0015.jpg",
            Location(22.336093, 114.155288, "Cheung Sha Wan")
        )
        val deliveries1 = Deliveries(
            1,
            "Deliver parcel to Leviero",
            "http://www.memoryandjustice.org/wp-content/uploads/2017/10/impossibly-cute-puppy-8.jpg",
            Location(22.319181, 114.170008, "Mong Kok")
        )
        deliveriesDao.saveDeliveriesList(listOf(deliveries, deliveries1))
        deliveriesDao.clearDeliveriesList()
        val deliveriesList = deliveriesDao.getDeliveriesList(0, 5)

        assertTrue(deliveriesList.isEmpty())
    }
}