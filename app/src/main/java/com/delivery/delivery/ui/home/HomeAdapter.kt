package com.delivery.delivery.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delivery.delivery.R
import com.delivery.delivery.model.Deliveries
import com.delivery.delivery.util.Constant
import kotlinx.android.synthetic.main.item_deliveries.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val deliveriesList = ArrayList<Deliveries>()
    var lazyLoadListener: LazyLoadListener? = null

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
        return ViewHolder(view)
    }

    override fun getItemCount() = deliveriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lazyLoadListener?.let {
            if (position >= deliveriesList.size - Constant.PAGING_THRESHOLD) {
                it.onThresholdReached(deliveriesList.size)
            }
        }
        holder.bind(deliveriesList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(deliveries: Deliveries) {
            itemView.tvInfo.text = deliveries.description
        }
    }

    interface LazyLoadListener {
        fun onThresholdReached(itemSize: Int)
    }
}