package com.darshan.androidtutorial.utils

import android.content.Context
import android.net.ConnectivityManager


fun Context.isNetworkConnected(): Boolean {
    val connectivityManager =
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.activeNetworkInfo.also {
        return it != null && it.isConnected
    }
    //  return connectivityManager?.activeNetworkInfo?.state == NetworkInfo.State.CONNECTED
}