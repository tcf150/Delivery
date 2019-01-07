package com.delivery.delivery.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delivery.delivery.R
import com.delivery.delivery.base.BaseActivity
import com.delivery.delivery.di.home.HomeComponent
import com.delivery.delivery.model.Deliveries
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {
    @Inject
    lateinit var presenter: HomeContract.Presenter
    private val adapter = HomeAdapter()

    override fun contentView() = R.layout.activity_home

    override fun injectDependency() {
        val homeComponent = HomeComponent.init()
        homeComponent.inject(this)
    }

    override fun initPresenter() {
        presenter.attachView(this)
    }

    override fun setupView() {
        rvDeliveries.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.lazyLoadListener = object: HomeAdapter.LazyLoadListener {
            override fun onThresholdReached(itemSize: Int) {
                presenter.loadMoreDeliveries(itemSize)
            }

        }
        rvDeliveries.adapter = adapter
    }

    override fun addDeliveriesList(deliveries: ArrayList<Deliveries>) {
        adapter.addDeliveriesList(deliveries)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
