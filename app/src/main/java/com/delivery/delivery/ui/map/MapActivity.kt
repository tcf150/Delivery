package com.delivery.delivery.ui.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.delivery.delivery.R
import com.delivery.delivery.base.BaseActivity
import com.delivery.delivery.base.GlideApp
import com.delivery.delivery.di.map.MapComponent
import com.delivery.delivery.model.Deliveries
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.item_deliveries.view.*
import javax.inject.Inject

class MapActivity : BaseActivity(),
    MapContract.View,
    OnMapReadyCallback {

    companion object {
        const val EXTRA_DELIVERIES = "MapActivity.Deliveries"
        fun start(context: Context, deliveries: Deliveries) {
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra(EXTRA_DELIVERIES, deliveries)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: MapContract.Presenter
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
    }

    override fun contentView() = R.layout.activity_map

    override fun initPresenter() {
        presenter.init(intent.getParcelableExtra(EXTRA_DELIVERIES))
        presenter.attachView(this)
    }

    override fun injectDependency() {
        val mapComponent = MapComponent.init()
        mapComponent.inject(this)
    }

    override fun setupView() {
        //no operation for now
    }

    override fun setDeliveriesTitle(title: String, location: String) {
        lyDeliveries.tvInfo.text = String.format(getString(R.string.txt_deliveries_title_format), title, location)
    }

    override fun setDeliveriesPhoto(imageUrl: String) {
        GlideApp.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(lyDeliveries.ivImage)
    }

    override fun setMarker(deliveries: Deliveries) {
        googleMap?.let {googleMap ->
            with(deliveries) {
                val latLng = LatLng(location.lat, location.lng)
                val markerOptions = MarkerOptions().position(latLng)
                    .title(String.format(getString(R.string.txt_deliveries_title_format), description, location.address))
                googleMap.addMarker(markerOptions)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16f)
                googleMap.moveCamera(cameraUpdate)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        presenter.onMapReady()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
        googleMap?.clear()
        googleMap = null
        map.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }
}