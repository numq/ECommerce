package com.numq.ecommerce.connection

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class ConnectionService(@ApplicationContext context: Context) {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isConnected: Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    } else {
        connectivityManager.activeNetworkInfo?.run { isConnectedOrConnecting && isAvailable } == true
    }

}