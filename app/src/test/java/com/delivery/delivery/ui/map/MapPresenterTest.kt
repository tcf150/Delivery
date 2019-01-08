package com.delivery.delivery.ui.map

import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.model.Location
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MapPresenterTest {

    @Mock
    lateinit var view: MapContract.View
    private lateinit var presenter: MapPresenter

    private val deliveries = Deliveries(
        0,
        "Deliver documents to Andrio",
        "https://www.what-dog.net/Images/faces2/scroll0015.jpg",
        Location(22.336093, 114.155288, "Cheung Sha Wan")
    )

    @Before
    fun setup() {
        presenter = MapPresenter()
        presenter.init(deliveries)
        presenter.attachView(view)
    }

    @Test
    fun onLoaded() {
        with(deliveries) {
            verify(view).setDeliveriesTitle(description, location.address)
            verify(view).setDeliveriesPhoto(imageUrl)
        }
    }

    @Test
    fun onMapReady() {
        presenter.onMapReady()
        verify(view).setMarker(deliveries)
    }

}