package com.sample.aboutcanada.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * This class is used to check the internet connectivity
 */
class ConnectionHelper {
    companion object {
        fun hasNetwork(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT < 23) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null) {
                    return networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                }
            } else {
                val activateNetwork = connectivityManager.activeNetwork
                if (activateNetwork != null) {
                    val networkCapabilities =
                        connectivityManager.getNetworkCapabilities(activateNetwork)
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
            }
            return false
        }
    }
}