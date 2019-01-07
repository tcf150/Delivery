package com.delivery.delivery.util

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkManager(connectivityManager: ConnectivityManager) {
    private var isConnected = false
    init {
        val callback = object: ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network?) {
                isConnected = false
            }

            override fun onAvailable(network: Network?) {
                isConnected = true
            }
        }
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build(),
            callback)

    }

    companion object {
        private lateinit var instance: NetworkManager

        fun init(connectivityManager: ConnectivityManager) {
            instance = NetworkManager(connectivityManager)
        }

        fun get() = instance
    }

    fun isConnected() = isConnected

}