package com.fantasy.components.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

enum class NetworkConnectStatus {
    Available, Unavailable, Losing, Lost
}

class NetworkConnectManager(
    private val context: Context = getContext
) {
    private val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @RequiresApi(Build.VERSION_CODES.N)
    fun observe(): Flow<NetworkConnectStatus> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch {
                    send(NetworkConnectStatus.Available)
                }
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                launch {
                    send(NetworkConnectStatus.Losing)
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch {
                    send(NetworkConnectStatus.Lost)
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                launch {
                    send(NetworkConnectStatus.Unavailable)
                }
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()
}