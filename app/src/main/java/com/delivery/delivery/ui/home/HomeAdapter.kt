package com.delivery.delivery.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delivery.delivery.R
import com.delivery.delivery.base.GlideRequests
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.util.Constant
import kotlinx.android.synthetic.main.item_deliveries.view.*

class HomeAdapter(val glideRequests: GlideRequests) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val deliveriesList = ArrayList<Deliveries>()
    var lazyLoadListener: LazyLoadListener? = null
    var onDeliveriesClickListener: OnDeliveriesClickListener? = null

    private val onClickListener = View.OnClickListener { view ->
        onDeliveriesClickListener?.let { listener ->
            val deliveries = view.tag
            if (deliveries is Deliveries) {
                listener.onDeliveriesClicked(deliveries)
            }
        }
    }

    fun addDeliveriesList(deliveriesList: ArrayList<Deliveries>) {
        this.deliveriesList.addAll(deliveriesList)
        notifyDataSetChanged()
    }

    fun clearDeliveries() {
        deliveriesList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_deliveries, parent, false)
        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount() = deliveriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lazyLoadListener?.let {
            if (position >= deliveriesList.size - Constant.PAGING_THRESHOLD) {
                it.onThresholdReached(deliveriesList.size)
            }
        }
        holder.bind(glideRequests, deliveriesList[position])
    }

    class ViewHolder(itemView: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(onClickListener)
        }

        fun bind(glideRequests: GlideRequests, deliveries: Deliveries) {
            with(itemView) {
                tag = deliveries
                tvInfo.text = deliveries.description
                glideRequests.load(deliveries.imageUrl)
                    .centerCrop()
                    .into(ivImage)
            }
        }
    }

    interface OnDeliveriesClickListener {
        fun onDeliveriesClicked(deliveries: Deliveries)
    }

    interface LazyLoadListener {
        fun onThresholdReached(itemSize: Int)
    }
}