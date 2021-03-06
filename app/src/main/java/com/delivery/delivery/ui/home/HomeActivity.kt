package com.delivery.delivery.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delivery.delivery.R
import com.delivery.delivery.base.BaseActivity
import com.delivery.delivery.base.GlideApp
import com.delivery.delivery.di.home.HomeComponent
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.ui.map.MapActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    companion object {
        const val EXTRA_DELIVERIES_DATA = "deliveriesData"
    }

    @Inject
    lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter: HomeAdapter

    override fun contentView() = R.layout.activity_home

    override fun injectDependency() {
        val homeComponent = HomeComponent.init()
        homeComponent.inject(this)
    }

    override fun initPresenter(savedInstanceState: Bundle?) {
        presenter.attachView(this)
        if (savedInstanceState != null) {
            val deliveriesList: ArrayList<Deliveries>? = savedInstanceState.getParcelableArrayList(EXTRA_DELIVERIES_DATA)
            deliveriesList?.let {
                presenter.init(it)
            }
        } else {
            presenter.loadMoreDeliveries(0, false)
        }
    }

    override fun setupView() {
        swipeRefreshLayout.setOnRefreshListener { presenter.loadMoreDeliveries(0, true) }
        rvDeliveries.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = HomeAdapter(GlideApp.with(this))
        adapter.onDeliveriesClickListener = object : HomeAdapter.OnDeliveriesClickListener {
            override fun onDeliveriesClicked(deliveries: Deliveries) {
                presenter.onDeliveriesClicked(deliveries)
            }
        }
        adapter.lazyLoadListener = object: HomeAdapter.LazyLoadListener {
            override fun onThresholdReached(itemSize: Int) {
                presenter.loadMoreDeliveries(itemSize)
            }

        }
        rvDeliveries.adapter = adapter
    }

    override fun addDeliveriesList(deliveries: List<Deliveries>) {
        adapter.addDeliveriesList(deliveries.toMutableList())
    }

    override fun clearDeliveriesList() {
        adapter.clearDeliveries()
    }

    override fun launchMapView(deliveries: Deliveries) {
        MapActivity.start(this, deliveries)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_DELIVERIES_DATA, adapter.deliveriesList)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
