package com.darshan.androidtutorial.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected(context.applicationContext)) {
            throw NoInternetException("No internet available")
        }
        return chain.proceed(chain.request())
    }
}

fun isNetworkConnected(applicationContext: Context): Boolean {
    val connectivityManager =
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.activeNetworkInfo.also {
        return it != null && it.isConnected
    }
}