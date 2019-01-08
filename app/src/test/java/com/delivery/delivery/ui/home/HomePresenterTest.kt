package com.delivery.delivery.ui.home

import com.delivery.delivery.data.repository.DeliveriesRepository
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.model.Location
import com.delivery.delivery.unit.test.RxJavaRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.powermock.modules.junit4.PowerMockRunner
import rx.Observable
import java.util.concurrent.TimeUnit

@RunWith(PowerMockRunner::class)
class HomePresenterTest {
    @Rule
    val rule = MockitoJUnit.rule()!!
    @Rule
    val rxJavaRule = RxJavaRule()

    @Mock
    lateinit var view: HomeContract.View
    @Mock
    lateinit var deliveriesRepository: DeliveriesRepository
    lateinit var presenter: HomePresenter

    private val deliveriesList = generateDeliveriesList()

    @Before
    fun setup() {
        `when`(deliveriesRepository.getDeliveries(Matchers.anyInt(), Matchers.anyInt()))
            .thenReturn(Observable.just(deliveriesList))
        presenter = HomePresenter(deliveriesRepository)
        presenter.attachView(view)
    }

    @Test
    fun onLoaded() {
        presenter.onLoaded()
        rxJavaRule.getTestScheduler().advanceTimeBy(1, TimeUnit.SECONDS)
        verify(view).addDeliveriesList(deliveriesList)
    }

    @Test
    fun onDeliveriesClicked() {
        presenter.onDeliveriesClicked(deliveriesList[0])
        verify(view).launchMapView(deliveriesList[0])
    }

    private fun generateDeliveriesList(): ArrayList<Deliveries> {
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
        return arrayListOf(deliveries, deliveries1)
    }
}