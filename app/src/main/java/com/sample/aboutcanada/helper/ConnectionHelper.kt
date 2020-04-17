package com.sample.aboutcanada.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionHelper {
    companion object {
        fun hasNetwork(context: Context): Boolean? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}